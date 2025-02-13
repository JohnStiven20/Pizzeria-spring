package com.example.spring.pizzeria.service;

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
    public void delete(Cliente cliente)  {
         if (clienteRepository.existsByEmail(cliente.getEmail())) {
            clienteRepository.delete(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No existe el cliente") ;

        }
    }

    @Override
    public Cliente update(Cliente cliente)  {
        
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            return clienteRepository.save(cliente);
        } else {
            System.out.println("VIVA ESPAÑA");
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No existe el cliente");
        }
    }

    @Override
    public Cliente save(Cliente cliente)  {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el cliente");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente getClienteByEmail(String gmail)  {

        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(gmail);

        if (clienteOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se ha encontrado el cliente");
        }
        return clienteOptional.get();
    }

    public List<Cliente> getAllCustormesByNombre(String nombre){
        return clienteRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Cliente> getAllCustomers() {
        return clienteRepository.findAll();
    }
    

}
