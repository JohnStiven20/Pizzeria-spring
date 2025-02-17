package com.example.spring.pizzeria.Interfaces;


import com.example.spring.pizzeria.MetodosPagos.PagarEfectivo;
import com.example.spring.pizzeria.MetodosPagos.PagarTarjeta;
import com.example.spring.pizzeria.model.Pedido;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

/**
 * Pagable
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_pago")
@JsonSubTypes({
    @JsonSubTypes.Type(value = PagarTarjeta.class, name = "PagarTarjeta"),
    @JsonSubTypes.Type(value = PagarEfectivo.class, name = "PagarEfectivo")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pago", discriminatorType = DiscriminatorType.STRING)
public abstract class Pagable {

    @OneToOne(mappedBy = "pagable")
    Pedido pedido;

    public Pagable() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pagable other = (Pagable) obj;
        if (pedido == null) {
            if (other.pedido != null)
                return false;
        } else if (!pedido.equals(other.pedido))
            return false;
        if (id != other.id)
            return false;
        return true;
    }

    public abstract int pagar();

}