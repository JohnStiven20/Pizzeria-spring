package com.example.spring.pizzeria.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void delete(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(cliente.getEmail());
        if (clienteOptional.isPresent()) {
            clienteRepository.delete(clienteOptional.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con email: " + cliente.getEmail());
        }
    }

    @Override
    public Cliente update(Cliente cliente) {
        return clienteRepository.findByEmail(cliente.getEmail())
                .map(clienteExistente -> clienteRepository.save(cliente))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con email: " + cliente.getEmail()));
    }

    @Override
    public Cliente save(Cliente cliente) {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El cliente con DNI " + cliente.getDni() + " ya existe");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado con email: " + email));
    }

    @Override
    public List<Cliente> getAllCustormesByNombre(String nombre) {
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Cliente> getAllCustomers() {
        return clienteRepository.findAll();
    }
}
