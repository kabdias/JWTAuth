package com.example.auth.Repository;

import com.example.auth.Entity.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Long> {

    Optional<Validation> findByCode(String code);

}
