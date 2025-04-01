package com.product.app.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;
    private String productDescription;
    private int productPrice;

    private User seller;

    @ManyToOne
    private Category category;
}

