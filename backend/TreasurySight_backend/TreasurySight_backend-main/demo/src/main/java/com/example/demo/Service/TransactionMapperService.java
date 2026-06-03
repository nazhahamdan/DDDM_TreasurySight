package com.example.demo.Service;

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
    private final EventRepository evenementRepo;

    public void saveRawTransaction(RawTransaction raw, Long entrepriseId, Long compteId) {
        Transaction t = new Transaction();
        t.setDescription(raw.getDescription());
        t.setMontant(raw.getAmount());
        t.setDateTransaction(raw.getDate());
        t.setSousCategorie(raw.getSubCategory());
        t.setTypeOperation(raw.getTypeOperation());
        t.setIdEntreprise(entrepriseId);
        t.setIdCompte(compteId);
        t.setSourceTransaction(SourceTransaction.API_BANCAIRE);
        t.setStatutTransaction(StatutTransaction.REALISE);

        transactionRepo.save(t);
    }
}
