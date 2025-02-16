package com.example.spring.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.pizzeria.model.Producto;
import com.example.spring.pizzeria.service.producto.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping()
    public Producto save(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @GetMapping
    public List<Producto> getAllProducts() {
        return productoService.findAll();
    }

    @PutMapping()
    public Producto update(@RequestBody Producto producto) {
        return productoService.update(producto);
    }

    @DeleteMapping()
    public void delete(@RequestBody Producto producto) {
        productoService.delete(producto);
    }
    
}
