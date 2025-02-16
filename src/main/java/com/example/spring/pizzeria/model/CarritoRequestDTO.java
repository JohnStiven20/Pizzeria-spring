package com.example.spring.pizzeria.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoRequestDTO {

    private int cantidad;
    private Producto producto;
    private Cliente cliente;
}