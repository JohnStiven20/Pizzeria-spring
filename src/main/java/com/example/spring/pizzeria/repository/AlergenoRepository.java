package com.example.spring.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Alergeno;

public interface AlergenoRepository extends JpaRepository<Alergeno, Integer> {
    
    Optional<Alergeno> findByNombre(String nombre);
    
}
