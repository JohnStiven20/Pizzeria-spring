package com.example.spring.pizzeria.model;

import com.example.spring.pizzeria.Interfaces.Pagable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class FinalizarPedidoRequestDTO {
 
     private Cliente cliente ;
     private Pagable pagable ; 

}
