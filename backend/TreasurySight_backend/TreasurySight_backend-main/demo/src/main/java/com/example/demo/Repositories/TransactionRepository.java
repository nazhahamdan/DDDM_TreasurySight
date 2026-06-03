package com.example.demo.Repositories;

import com.example.demo.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByEntrepriseId(Long entrepriseId);

    @Query("SELECT t FROM Transaction t WHERE t.entreprise.id = :entrepriseId AND t.statut = 'REALISE'")
    List<Transaction> findRealisedByEntreprise(Long entrepriseId);
}