//package com.product.app.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Entity
//@Data
//public class CartProduct {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer cpId;
//
//    @ManyToOne
//    @JsonIgnore
//    private Cart cart;
//
//    @ManyToOne
//    private Product product;
//
//    private Integer quantity;
//
//}