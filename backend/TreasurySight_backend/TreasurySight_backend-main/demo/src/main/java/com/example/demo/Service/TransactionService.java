package com.example.demo.Service;

import com.example.demo.Entities.Entreprise;
import com.example.demo.Entities.Transaction;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.dto.RawTransaction;
import com.example.demo.enums.SousCategorie;

import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionService {
    private final TransactionRepository repo;

    public TransactionService(TransactionRepository repo) {
        this.repo = repo;
    }

    // GET ALL (à éviter en prod)
    public List<Transaction> getAll() {
        return repo.findAll();
    }

    //  GET BY ENTREPRISE
    public List<Transaction> getByEntreprise(Long idEntreprise) {
        return repo.findByEntrepriseId(idEntreprise);
    }

    // GET BY ID
    public Transaction getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction non trouvée"));
    }

    // CREATE
    public Transaction create(RawTransaction dto, int entrepriseId) {

        Entreprise e = new Entreprise();
        e.setId(entrepriseId);

        Transaction t = new Transaction();

        t.setDescription(dto.getDescription());
        t.setMontant(dto.getAmount());
        t.setTypeOperation(dto.getTypeOperation());
        t.setDateTransaction(dto.getDate());
        t.setDatePaiement(dto.getDatePaiement());
        t.setSousCategorie(dto.getSubCategory());

        t.setEntreprise(e);

        if (dto.getSubCategory() == SousCategorie.FACTURE_CLIENT) {
            if (dto.getClient() == null || dto.getClient().isBlank()) {
                throw new IllegalArgumentException(
                    "Client obligatoire pour FACTURE_CLIENT"
                );
            }
            t.setClient(dto.getClient());
        }

        return repo.save(t);
    }
        

    // UPDATE
    public Transaction update(Long id, RawTransaction dto) {

        Transaction existing = getById(id);

        existing.setDescription(dto.getDescription());
        existing.setMontant(dto.getAmount());
        existing.setTypeOperation(dto.getTypeOperation());
        existing.setDateTransaction(dto.getDate());
        existing.setDatePaiement(dto.getDatePaiement());
        existing.setSousCategorie(dto.getSubCategory());

        // 👇 RULE CLIENT UPDATE
        if (dto.getSubCategory() == SousCategorie.FACTURE_CLIENT) {

            if (dto.getClient() == null || dto.getClient().isBlank()) {
                throw new IllegalArgumentException(
                    "Client obligatoire pour FACTURE_CLIENT"
                );
            }

            existing.setClient(dto.getClient());
        } else {
            existing.setClient(null); // important cohérence
        }

        existing.setTauxTva(existing.getTauxTva());
        existing.setSource(existing.getSource());
        existing.setReferenceExterne(existing.getReferenceExterne());
        existing.setCategoriseAuto(existing.getCategoriseAuto());
        existing.setStatut(existing.getStatut());
        existing.setCompte(existing.getCompte());

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
