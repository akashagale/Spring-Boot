package com.product.app.Entity;

import jakarta.persistence.*;

import java.util.List;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String userName;
    private String password;

    @ManyToMany
    private List<Role> roles;

    @OneToOne
    private Cart cart;





}
