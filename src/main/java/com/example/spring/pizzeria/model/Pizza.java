package com.example.spring.pizzeria.model;
import com.example.spring.pizzeria.Enums.Size;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue(value="Pizza")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza  extends Producto{

    private Size size;

}