package com.example.spring.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.pizzeria.model.CarritoRequestDTO;
import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.model.FinalizarPedidoRequestDTO;
import com.example.spring.pizzeria.model.Pedido;
import com.example.spring.pizzeria.service.pedido.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping()
    public Pedido save(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    @GetMapping()
    public List<Pedido> getAllOrders() {
        return pedidoService.findAll();
    }

    @PutMapping()
    public Pedido update(@RequestBody Pedido pedido) {
        return pedidoService.update(pedido);
    }

    @DeleteMapping()
    public void delete(Pedido pedido) {
        pedidoService.delete(pedido);
    }

    @PostMapping("/carrito")
    public Pedido addCarrito(@RequestBody CarritoRequestDTO carritoRequestDTO) {
        return pedidoService.addCarrito(carritoRequestDTO.getCliente(), carritoRequestDTO.getProducto(), carritoRequestDTO.getCantidad());
    }

    @PutMapping("/finalizar")
    public Pedido finalizaPedido(@RequestBody FinalizarPedidoRequestDTO finalizarPedidoRequestDTO) {
        return pedidoService.finalizaPedido(finalizarPedidoRequestDTO.getCliente(), finalizarPedidoRequestDTO.getPagable());
    }

    @PutMapping("/cancelar")
    public Pedido cancelarPedido(@RequestBody Cliente cliente) {
        return pedidoService.cancelarPedido(cliente);
    }

    @PutMapping("/entregar/{pedidoId}")
    public Pedido entregarPedido(@PathVariable int pedidoId) {
        return pedidoService.entregarPedido(pedidoId);
    }
    
}
