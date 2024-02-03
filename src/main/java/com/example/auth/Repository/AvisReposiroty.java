package com.example.auth.Repository;

import com.example.auth.Entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AvisReposiroty extends JpaRepository<Avis, Long> {
}
