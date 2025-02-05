package com.example.spring.pizzeria.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente  {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(unique=true)
    private String  dni;
    private String nombre;
    @Column(unique=true, nullable=false)
    private String telefono;
    @Column(unique=true, nullable=false)
    private String email;
    private String password;
    private Boolean admin;
    private String direccion;
    private String apellidos;
    
    @OneToMany(mappedBy="cliente", cascade={CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval=false) 
    private List<Pedido> listaPedidos;

}
