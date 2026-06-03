package com.example.demo.Repositories;

import com.example.demo.Entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Integer> {

    Optional<Entreprise> findByNom(String nom);

    Optional<Entreprise> findById(Long id);
}
