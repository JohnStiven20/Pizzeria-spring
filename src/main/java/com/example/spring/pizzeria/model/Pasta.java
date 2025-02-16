package com.example.spring.pizzeria.model;

import java.util.List;

import com.example.spring.pizzeria.Enums.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Entity
@DiscriminatorValue(value="Pasta")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pasta extends Producto{

    @Builder
    public Pasta() {
    }

    @Builder
    public Pasta(int id, String nombre, double precio, Size size, List<Ingrediente> productos) {
        super(id, nombre, precio, size, productos);
    }

}
