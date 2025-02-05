package com.example.spring.pizzeria.model;

import java.util.List;

import com.example.spring.pizzeria.Enums.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = SINGLE_TABLE) 
@DiscriminatorColumn(name="producto_tipo",  discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
    protected String nombre;
    protected double precio;
    @Enumerated(EnumType.STRING)
    private Size size;

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    private List<Ingrediente> ingredientes;
    
}
