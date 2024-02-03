package com.example.auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "avis")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String message;
    private String status;

}
