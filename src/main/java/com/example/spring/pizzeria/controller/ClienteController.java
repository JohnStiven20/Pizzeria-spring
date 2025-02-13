package com.example.spring.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.service.ClienteService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    

    @Autowired
    private ClienteService clienteService;

    @PostMapping()
    public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @GetMapping
    public List<Cliente> getAllCustomers(@RequestParam(value = "nombre", required = false) String nombre) {
        
        if (nombre != null) {
            return clienteService.getAllCustormesByNombre(nombre);
        } else {
            return clienteService.getAllCustomers();
        }
    }

    @PutMapping()
    public Cliente update(@RequestBody Cliente cliente) {
        return clienteService.update(cliente);
    }

    @DeleteMapping()
    public void delete(@RequestBody Cliente cliente) {
        clienteService.delete(cliente);
    }

}
