package com.example.demo.Service;

import com.example.demo.Entities.CompteBancaire;
import com.example.demo.Entities.Entreprise;
import com.example.demo.Repositories.CompteBancaireRepository;
import com.example.demo.Repositories.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.dto.RawTransaction;
import com.example.demo.Entities.Transaction;
import com.example.demo.enums.SourceTransaction;
import com.example.demo.enums.StatutTransaction;
import com.example.demo.Repositories.TransactionRepository;
import com.example.demo.Repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class TransactionMapperService {

    private final TransactionRepository transactionRepo;
    private final EntrepriseRepository entrepriseRepo;
    private final CompteBancaireRepository compteBancaireRepo;

    public Transaction saveRawTransaction(RawTransaction raw, Long entrepriseId, Long compteId) {
        Entreprise entreprise = entrepriseRepo.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable : " + entrepriseId));

        CompteBancaire compte = compteBancaireRepo.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte introuvable : " + compteId));

        Transaction t = new Transaction();
        t.setDescription(raw.getDescription());
        t.setMontant(raw.getAmount());
        t.setDateTransaction(raw.getDate());
        t.setSousCategorie(raw.getSubCategory());
        t.setTypeOperation(raw.getTypeOperation());
        t.setEntreprise(entreprise);   // ← objet, pas un Long
        t.setCompte(compte);           // ← objet, pas un Long
        t.setSource(SourceTransaction.API_BANCAIRE);
        t.setStatut(StatutTransaction.REALISE);

        return transactionRepo.save(t);
    }
}
