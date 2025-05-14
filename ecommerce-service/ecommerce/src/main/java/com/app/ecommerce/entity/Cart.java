package com.app.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    private Double totalAmount;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_Id",unique = true
    )
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(Integer cartId, Double totalAmount, User user, List<CartProduct> cartProducts) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.user = user;
        this.cartProducts = cartProducts;
    }


    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalAmount=" + totalAmount +
                ", cartProductsCount=" + (cartProducts != null ? cartProducts.size() : 0) +
                '}';
    }
}