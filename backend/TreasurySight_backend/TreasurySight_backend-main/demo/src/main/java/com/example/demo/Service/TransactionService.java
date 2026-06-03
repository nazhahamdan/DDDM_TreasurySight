package com.example.demo.Service;

import com.example.demo.Entities.Entreprise;
import com.example.demo.Entities.Transaction;
import com.example.demo.Repositories.TransactionRepository;
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

    // CREATE (sécurisé par entreprise)
    public Transaction create(Transaction t, int entrepriseId) {

        Entreprise e = new Entreprise();
        e.setId(entrepriseId);

        t.setEntreprise(e);

        return repo.save(t);
    }

    // UPDATE
    public Transaction update(Long id, Transaction t) {

        Transaction existing = getById(id);

        existing.setDescription(t.getDescription());
        existing.setMontant(t.getMontant());
        existing.setTypeOperation(t.getTypeOperation());
        existing.setDateTransaction(t.getDateTransaction());
        existing.setSousCategorie(t.getSousCategorie());
        existing.setTauxTva(t.getTauxTva());
        existing.setSource(t.getSource());
        existing.setReferenceExterne(t.getReferenceExterne());
        existing.setCategoriseAuto(t.getCategoriseAuto());
        existing.setStatut(t.getStatut());
        existing.setCompte(t.getCompte());

        return repo.save(existing);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
