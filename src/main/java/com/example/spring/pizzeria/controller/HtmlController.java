package com.example.spring.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.spring.pizzeria.model.Cliente;
import com.example.spring.pizzeria.service.cliente.ClienteService;
import org.springframework.ui.Model;

@Controller
public class HtmlController {
    
    @Autowired(required = true)
    private ClienteService clienteService;


    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.getAllCustomers();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/clientes/{id}")
    public String detalleCliente(@PathVariable int id, Model model) {
        Cliente cliente = clienteService.findClienteById(id);
        model.addAttribute("cliente", cliente);
        return "cliente-detalle"; 
    }

}
