package com.example.demo.Service;

import com.example.demo.Entities.Transaction;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.dto.ClientRiskDTO;
import com.example.demo.enums.SousCategorie;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsClientService {

    private final TransactionRepository transactionRepository;

    public AnalyticsClientService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<ClientRiskDTO> getClientAnalysis(Long entrepriseId) {

        List<Transaction> transactions =
                transactionRepository.findByEntrepriseId(entrepriseId)
                        .stream()
                        .filter(t -> t.getSousCategorie() == SousCategorie.FACTURE_CLIENT)
                        .filter(t -> t.getClient() != null)
                        .filter(t -> !t.getClient().isBlank())
                        .toList();

        Map<String, List<Transaction>> byClient =
                transactions.stream()
                        .collect(Collectors.groupingBy(Transaction::getClient));

        List<ClientRiskDTO> result = new ArrayList<>();

        for (Map.Entry<String, List<Transaction>> entry : byClient.entrySet()) {

            String client = entry.getKey();
            List<Transaction> clientTransactions = entry.getValue();

            double totalCa = 0;
            double overdueAmount = 0;

            long totalDelay = 0;
            long lateInvoiceCount = 0;

            for (Transaction t : clientTransactions) {

                totalCa += t.getMontant();

                if (t.getDatePaiement() != null) {

                    long delay = ChronoUnit.DAYS.between(
                            t.getDateTransaction(),
                            t.getDatePaiement()
                    );

                    if (delay > 0) {

                        totalDelay += delay;
                        lateInvoiceCount++;

                        overdueAmount += t.getMontant();
                    }
                }
            }

            double averageDelay =
                    lateInvoiceCount == 0
                            ? 0
                            : (double) totalDelay / lateInvoiceCount;

            ClientRiskDTO dto = new ClientRiskDTO();

            dto.setClient(client);
            dto.setTotalCa(totalCa);
            dto.setOverdueAmount(overdueAmount);
            dto.setAverageDelay(averageDelay);

            dto.setInvoiceCount(
                    (long) clientTransactions.size()
            );

            dto.setLateInvoiceCount(
                    lateInvoiceCount
            );

            dto.setScore(
                    calculateScore(
                            totalCa,
                            averageDelay,
                            overdueAmount
                    )
            );

            result.add(dto);
        }

        result.sort(
                Comparator.comparing(ClientRiskDTO::getScore)
                        .reversed()
        );

        return result;
    }

    private double calculateScore(
            double totalCa,
            double averageDelay,
            double overdueAmount
    ) {

        double delayComponent =
                Math.min(averageDelay * 2, 60);

        double overdueRatio =
                totalCa == 0
                        ? 0
                        : overdueAmount / totalCa;

        double overdueComponent =
                overdueRatio * 40;

        return Math.round(
                Math.min(
                        100,
                        delayComponent + overdueComponent
                )
        );
    }
}