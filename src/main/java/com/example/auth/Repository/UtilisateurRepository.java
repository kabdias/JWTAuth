package com.example.auth.Repository;

import com.example.auth.Entity.Utilisateur;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByMail(String mail);
}
