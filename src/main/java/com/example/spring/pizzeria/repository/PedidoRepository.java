package com.example.spring.pizzeria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.model.Pedido;
import com.example.spring.pizzeria.model.Pedido.EstadoPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    Optional<List<Pedido>> findByEstadoAndCliente(EstadoPedido Estado, Cliente cliente);

}
