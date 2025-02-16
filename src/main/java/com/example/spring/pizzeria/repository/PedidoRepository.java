package com.example.spring.pizzeria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    Optional<List<Pedido>> findByEstadoAndCliente(String Estado, Cliente cliente);

}
