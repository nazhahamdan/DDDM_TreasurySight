package com.example.demo.Repositories;

import com.example.demo.Entities.Evenement;
import com.example.demo.enums.TypeEvenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Evenement,Long> {

    List<Evenement> findByEntrepriseIdAndType(Long entreprise_id, TypeEvenement typeEvenement);
    List<Evenement> findByEntrepriseId(Long entreprise_id);

    List<Evenement> findByEntrepriseIdAndDateEcheanceAfter(
        Long entrepriseId,
        LocalDate date
    );
}
