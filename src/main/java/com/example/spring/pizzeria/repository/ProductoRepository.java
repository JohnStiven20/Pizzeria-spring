package com.example.spring.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>  {

    boolean existsByNombre(String nombre);
    Optional<Producto> findByNombre(String nombre);
    
}
