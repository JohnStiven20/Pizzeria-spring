package com.example.spring.pizzeria.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    
    Optional<Ingrediente> findByNombre(String nombre);
    
}
