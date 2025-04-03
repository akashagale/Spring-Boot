//package com.product.app.Entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
////@Entity
////@Data
////@AllArgsConstructor
////@NoArgsConstructor
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer cartId;
//
//    private Double totalAmount;
//
//    @JsonIgnore
//    @OneToOne
//    private User user;
//
//    @OneToMany(mappedBy = "cart")
//    private List<CartProduct> cartProducts;
//}