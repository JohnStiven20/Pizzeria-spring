package com.example.spring.pizzeria.model;


import java.util.List;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alergeno {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;

    @ManyToMany(mappedBy="alergenos")
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude
    private List<Ingrediente> ingredientes;

}