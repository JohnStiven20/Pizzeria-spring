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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void update(Cliente cliente)  {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean save(Cliente cliente)  {
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe el usuario");
        }

        clienteRepository.save(cliente);
        return true;
    }

    @Override
    public Cliente getClienteByEmail(String gmail)  {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(gmail);

        if (clienteOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "No se ha encontrado el cliente");
        }
        return clienteOptional.get();
    }

    @Override
    public List<Cliente> getAllCustomers() {
        return clienteRepository.findAll();
    }
    

}
