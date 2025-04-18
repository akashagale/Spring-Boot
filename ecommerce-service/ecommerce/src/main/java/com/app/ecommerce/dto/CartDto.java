package com.app.ecommerce.dto;

import com.app.ecommerce.entity.CartProduct;
import com.app.ecommerce.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

public class CartDto {

    private Integer cartId;
    private Double totalAmount;
    private List<CartProduct> cartProducts;

    public CartDto() {
    }

    public CartDto(Integer cartId, Double totalAmount, List<CartProduct> cartProducts) {
        this.cartId = cartId;
        this.totalAmount = totalAmount;
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

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "cartId=" + cartId +
                ", totalAmount=" + totalAmount +
                ", cartProducts=" + cartProducts +
                '}';
    }
}