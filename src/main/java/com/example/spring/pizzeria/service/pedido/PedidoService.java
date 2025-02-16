package com.example.spring.pizzeria.service.pedido;

import java.util.List;
import java.util.Optional;

import com.example.spring.pizzeria.Interfaces.Pagable;
import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.model.Pedido;
import com.example.spring.pizzeria.model.Producto;

public interface PedidoService {

    void delete(Pedido pedido);

    Pedido update(Pedido pedido);

    Pedido save(Pedido pedido);

    List<Pedido> findAll();

    Optional<List<Pedido>> findByEstadoAndCliente(String estado, Cliente cliente);

    Pedido addCarrito(Cliente pedido, Producto producto, int cantidad);

    Pedido finalizaPedido(Cliente cliente, Pagable pago);

    Pedido entregarPedido(int pedido_id);

    Pedido cancelarPedido(Cliente cliente);

}
