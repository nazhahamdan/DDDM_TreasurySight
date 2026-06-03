package com.example.demo.Service;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.CategoryRowDTO;
import com.example.demo.dto.DashboardDTO;
import com.example.demo.dto.SubCategoryRowDTO;
import com.example.demo.Entities.Transaction;
import com.example.demo.Entities.Evenement;
import com.example.demo.enums.TypeOperation;
import com.example.demo.enums.Categorie;
import com.example.demo.enums.SousCategorie;
import com.example.demo.enums.StatutEvenement;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.Repositories.EventRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final TransactionRepository transactionRepository;
    private final EventRepository eventRepository;

    public DashboardDTO getDashboard(Long entrepriseId) {

        // ==============================
        // 1. Fetch data
        // ==============================
        List<Transaction> transactions = transactionRepository.findRealisedByEntreprise(entrepriseId);

        List<Evenement> events = eventRepository.findByEntrepriseIdAndDateEcheanceAfter(entrepriseId,LocalDate.now());

        // ==============================
        // 2. Build timeline (6 past + 3 future months)
        // ==============================
        List<String> months = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yy");

        // Past 6 months
        for (int i = 5; i >= 0; i--) {
            months.add(LocalDate.now().minusMonths(i).format(formatter));
        }

        // Future 3 months
        for (int i = 1; i <= 3; i++) {
            months.add(LocalDate.now().plusMonths(i).format(formatter));
        }

        // ==============================
        // 3. Aggregate transactions
        // ==============================
        Map<String, Double> cashInMap = new HashMap<>();
        Map<String, Double> cashOutMap = new HashMap<>();

        for (Transaction t : transactions) {
            String key = t.getDateTransaction().format(formatter);

            if (t.getTypeOperation() == TypeOperation.CREDIT) {
                cashInMap.put(key,
                        cashInMap.getOrDefault(key, 0.0) + t.getMontant());
            } else {
                cashOutMap.put(key,
                        cashOutMap.getOrDefault(key, 0.0) - t.getMontant());
            }
        }

        // ==============================
        // 4. Aggregate events (forecast)
        // ==============================
        for (Evenement e : events) {

            if (e.getStatut() != StatutEvenement.PREVU) continue;

            String key = e.getDateEcheance().format(formatter);

            if (!months.contains(key)) continue; // only future months

            if (e.getTypeOperation() == TypeOperation.CREDIT) {
                cashInMap.put(key,
                        cashInMap.getOrDefault(key, 0.0) + e.getMontant());
            } else {
                cashOutMap.put(key,
                        cashOutMap.getOrDefault(key, 0.0) - e.getMontant());
            }
        }

        // ==============================
        // 5. Build arrays
        // ==============================
        List<Double> cashIn = new ArrayList<>();
        List<Double> cashOut = new ArrayList<>();

        for (String m : months) {
            cashIn.add(cashInMap.getOrDefault(m, 0.0));
            cashOut.add(cashOutMap.getOrDefault(m, 0.0));
        }

        // ==============================
        // 6. Build balance
        // ==============================
        List<Double> balance = new ArrayList<>();
        double current = 0;
        // real treasury
        // double current = transactions.stream()
        //     .mapToDouble(t -> t.getTypeOperation() == TypeOperation.CREDIT
        //         ? t.getMontant()
        //         : -t.getMontant())
        //     .sum();

        for (int i = 0; i < months.size(); i++) {
            current += cashIn.get(i) + cashOut.get(i);
            balance.add(current);
        }

        // ==============================
        // 7. Forecast flags
        // ==============================
        List<Boolean> isForecast = new ArrayList<>();

        for (int i = 0; i < 6; i++) isForecast.add(false);
        for (int i = 0; i < 3; i++) isForecast.add(true);

        // ==============================
        // 8. Build DTO
        // ==============================
        DashboardDTO dto = new DashboardDTO();
        dto.setMonths(months);
        dto.setCashIn(cashIn);
        dto.setCashOut(cashOut);
        dto.setBalance(balance);
        dto.setIsForecast(isForecast);

        dto.setTable(buildCategoryTable(months, transactions, events));

        return dto;
    }

    private List<CategoryRowDTO> buildCategoryTable(
        List<String> months,
        List<Transaction> transactions,
        List<Evenement> events
    ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM yy");

        // ==============================
        // 1. Structure:
        // Category -> SubCategory -> Month -> Value
        // ==============================
        Map<Categorie, Map<SousCategorie, Map<String, Double>>> table = new LinkedHashMap<>();

        // Initialize all categories
        for (Categorie cat : Categorie.values()) {
            table.put(cat, new LinkedHashMap<>());
        }

        // ==============================
        // 2. Fill TRANSACTIONS
        // ==============================
        for (Transaction t : transactions) {

            if (t.getSousCategorie() == null) continue;

            SousCategorie sub = t.getSousCategorie();
            Categorie cat = sub.getCategorie();

            String month = t.getDateTransaction().format(formatter);

            double value = t.getTypeOperation() == TypeOperation.CREDIT
                    ? t.getMontant()
                    : -t.getMontant();

            table.putIfAbsent(cat, new LinkedHashMap<>());
            Map<SousCategorie, Map<String, Double>> subMap = table.get(cat);

            subMap.putIfAbsent(sub, initMonthMap(months));
            Map<String, Double> monthMap = subMap.get(sub);

            monthMap.put(month, monthMap.get(month) + value);
        }

        // ==============================
        // 3. Fill EVENTS (forecast)
        // ==============================
        for (Evenement e : events) {

            if (e.getStatut() != StatutEvenement.PREVU) continue;
            if (e.getSousCategorie() == null) continue;

            SousCategorie sub = e.getSousCategorie();
            Categorie cat = sub.getCategorie();

            String month = e.getDateEcheance().format(formatter);

            double value = e.getTypeOperation() == TypeOperation.CREDIT
                    ? e.getMontant()
                    : -e.getMontant();

            table.putIfAbsent(cat, new LinkedHashMap<>());
            Map<SousCategorie, Map<String, Double>> subMap = table.get(cat);

            subMap.putIfAbsent(sub, initMonthMap(months));
            Map<String, Double> monthMap = subMap.get(sub);

            monthMap.put(month, monthMap.get(month) + value);
        }

        // ==============================
        // 4. Convert to DTO
        // ==============================
        List<CategoryRowDTO> rows = new ArrayList<>();

        for (Map.Entry<Categorie, Map<SousCategorie, Map<String, Double>>> catEntry : table.entrySet()) {

            CategoryRowDTO catRow = new CategoryRowDTO();
            catRow.setCategory(catEntry.getKey().getLabel());

            List<Double> categoryTotals = initValuesList(months.size());
            List<SubCategoryRowDTO> subRows = new ArrayList<>();

            for (Map.Entry<SousCategorie, Map<String, Double>> subEntry : catEntry.getValue().entrySet()) {

                SubCategoryRowDTO subRow = new SubCategoryRowDTO();
                subRow.setSubCategory(subEntry.getKey().getLabel()); 

                List<Double> values = new ArrayList<>();

                int i = 0;
                for (String m : months) {
                    double val = subEntry.getValue().get(m);
                    values.add(val);

                    // accumulate into category total
                    categoryTotals.set(i, categoryTotals.get(i) + val);
                    i++;
                }

                subRow.setValues(values);
                subRows.add(subRow);
            }

            catRow.setValues(categoryTotals);
            catRow.setSubCategories(subRows);

            rows.add(catRow);
        }

        return rows;
    }

    private Map<String, Double> initMonthMap(List<String> months) {
        Map<String, Double> map = new HashMap<>();
        for (String m : months) {
            map.put(m, 0.0);
        }
        return map;
    }

    private List<Double> initValuesList(int size) {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(0.0);
        }
        return list;
    }
}