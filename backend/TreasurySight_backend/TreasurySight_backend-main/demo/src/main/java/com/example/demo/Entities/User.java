package com.example.demo.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    @Column(name = "mot-de-passe")
    private String  password;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @PrePersist
    protected  void onCreate(){
        createdAt=LocalDateTime.now();
    }
    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;
}