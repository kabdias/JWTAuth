package com.example.auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Entity
@Table(name= "validation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Instant creation;
    private Instant expiration;
    private Instant activation;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
}
