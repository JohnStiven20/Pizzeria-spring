package com.example.spring.pizzeria.service.pedido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.pizzeria.Interfaces.Pagable;
import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.model.LineaPedido;
import com.example.spring.pizzeria.model.Pedido;
import com.example.spring.pizzeria.model.Producto;
import com.example.spring.pizzeria.model.Pedido.EstadoPedido;
import com.example.spring.pizzeria.repository.ClienteRepository;
import com.example.spring.pizzeria.repository.LineaPedidoRepository;
import com.example.spring.pizzeria.repository.PedidoRepository;
import com.example.spring.pizzeria.repository.ProductoRepository;
import com.example.spring.pizzeria.service.cliente.ClienteService;
import com.example.spring.pizzeria.service.producto.ProductoService;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private LineaPedidoRepository lineaPedidoRepository;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ClienteService clienteService;

    @Override
    public void delete(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    @Override
    public Pedido update(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido save(Pedido pedido) {


        pedidoRepository.save(pedido);
        return pedido;
    }

    LineaPedido saveLineaPedido(LineaPedido lineaPedido) {
        Producto producto = productoService.save(lineaPedido.getProducto());
        lineaPedido.setProducto(producto);
        return lineaPedidoRepository.save(lineaPedido);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<List<Pedido>> findByEstadoAndCliente(EstadoPedido estado, Cliente cliente) {
        return pedidoRepository.findByEstadoAndCliente(estado, cliente);
    }

    @Override
    public Pedido addCarrito(Cliente cliente, Producto producto, int cantidad) {

        Optional<List<Pedido>> pedidosOptional = pedidoRepository.findByEstadoAndCliente(EstadoPedido.PEDIENTE, cliente);

        int cantidadPedidos = pedidosOptional.map(List::size).orElse(0);
        
        if (cantidadPedidos >= 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se puede tener más de un pedido pendiente");
        }

        Cliente clienteConContexto = clienteService.update(cliente);

        Pedido pedido = pedidoRepository
                .findByEstadoAndCliente(EstadoPedido.PEDIENTE, clienteConContexto).get().stream()
                .findFirst()
                .orElse(new Pedido(0, new Date(), EstadoPedido.PEDIENTE, new ArrayList<>(), clienteConContexto, null));

        Pedido pedidoBaseDatos = pedidoRepository.save(pedido);
        LineaPedido lineaPedido = saveLineaPedido(new LineaPedido(0, cantidad, producto, pedidoBaseDatos));
        pedidoBaseDatos.getLineaPedidos().add(lineaPedido);
        Pedido pedidoGuardado = pedidoRepository.save(pedidoBaseDatos);
        return pedidoGuardado;
    } 

    @Override
    public Pedido finalizaPedido(Cliente cliente, Pagable pago) {

        Pedido pedido = pedidoRepository.findByEstadoAndCliente(EstadoPedido.PEDIENTE, cliente)
                .flatMap(pedidos -> pedidos.stream().findFirst())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No hay pedidos pendientes para este cliente"));

        pedido.setPagable(pago);
        pedido.setEstado(EstadoPedido.ENTREGADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido entregarPedido(int pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido no encontrado"));

        pedido.setEstado(EstadoPedido.ENTREGADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido cancelarPedido(Cliente cliente) {
        Pedido pedido = pedidoRepository.findByEstadoAndCliente(EstadoPedido.PEDIENTE, cliente)
                .flatMap(pedidos -> pedidos.stream().findFirst())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No hay pedidos pendientes para este cliente"));

        pedido.setEstado(EstadoPedido.CANCELADO);
        return pedidoRepository.save(pedido);
    }
}
