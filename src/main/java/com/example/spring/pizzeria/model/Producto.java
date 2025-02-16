package com.example.spring.pizzeria.model;

import java.util.List;

import com.example.spring.pizzeria.Enums.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Pizza.class, name = "Pizza"),
    @JsonSubTypes.Type(value = Bebida.class, name = "Bebida"),
    @JsonSubTypes.Type(value = Pasta.class, name = "Pasta")
})
public abstract class Producto {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int id;
    protected String nombre;
    protected double precio;
    @Enumerated(EnumType.STRING)
    private Size size;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
    private List<Ingrediente> ingredientes;
    
}
