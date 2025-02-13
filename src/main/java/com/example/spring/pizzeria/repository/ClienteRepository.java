package com.example.spring.pizzeria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    boolean existsByDni(String dni);
    Optional<Cliente> findByDni(String dni);
    Optional<Cliente> findByEmail(String email);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByEmail(String email);


}
