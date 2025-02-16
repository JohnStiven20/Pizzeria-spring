package com.example.spring.pizzeria.model;

import java.util.Date;
import java.util.List;

import com.example.spring.pizzeria.Interfaces.Pagable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    public enum EstadoPedido {
        PEDIENTE, ENTREGADO, CANCELADO
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Date date
    ;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<LineaPedido> lineaPedidos;
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "cliente_id", nullable = true)
    @JsonIgnore
    private Cliente cliente;
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "pagable_id")
    private Pagable pagable;


}
