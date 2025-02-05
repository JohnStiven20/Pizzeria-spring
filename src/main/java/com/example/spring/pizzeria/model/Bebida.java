package com.example.spring.pizzeria.model;


import java.util.List;

import com.example.spring.pizzeria.Enums.Size;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue(value = "Bebida")
@Getter
@Setter
public class Bebida extends Producto {


    @Builder
    public Bebida(int id, String nombre, double precio, Size size, List<Ingrediente> ingredientes) {
        super(id, nombre, precio, size, ingredientes);
    }

    @Builder
    public Bebida() {
    }

}
