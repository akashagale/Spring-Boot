package com.app.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "cart_id", "product_id" }) })
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cpId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity = 1;

    public CartProduct() {
    }

    public CartProduct(Cart cart, Integer quantity, Product product) {
        this.cart = cart;
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "cpId=" + cpId +
                ", cart=" + cart +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}