package com.example.spring.pizzeria.service;

import java.util.List;

import com.example.spring.pizzeria.model.Cliente;

public interface ClienteService {

    void delete(Cliente cliente);

    Cliente update(Cliente cliente) ;

    Cliente save(Cliente cliente) ;

    Cliente getClienteByEmail(String gmail) ;

    List<Cliente> getAllCustomers();

    List<Cliente> getAllCustormesByNombre(String nombre);
    
}
