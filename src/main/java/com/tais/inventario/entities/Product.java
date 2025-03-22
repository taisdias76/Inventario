package com.tais.inventario.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private BigDecimal quantity;

}
