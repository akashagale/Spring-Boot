package com.product.app.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cpId;

    @ManyToOne
    private Cart cart;

    private Product product;

    private Integer quantity;

}