package com.product.app.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Cart {

    private Integer cartId;
    private Double totalAmount;

    @OneToOne
    private User user;

    @OneToMany
    private List<CartProduct> cartProducts;
}