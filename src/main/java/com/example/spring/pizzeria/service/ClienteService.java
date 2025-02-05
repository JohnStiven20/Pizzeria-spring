package com.example.spring.pizzeria.service;

import java.util.List;

import com.example.spring.pizzeria.model.Cliente;

public interface ClienteService {

    void delete(Cliente cliente);

    void update(Cliente cliente) ;

    boolean save(Cliente cliente) ;

    Cliente getClienteByEmail(String gmail) ;

    List<Cliente> getAllCustomers();
    
}
