package com.example.spring.pizzeria.service.producto;

import java.util.List;

import com.example.spring.pizzeria.model.Producto;

public interface ProductoService {

    void delete(Producto producto);

    Producto update(Producto producto);

    Producto save(Producto producto);

    List<Producto> findAll();
    
}
