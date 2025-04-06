package com.app.ecommerce.entity;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull(message = "Product name is required")
    @Size(min = '5', message = "Product name must be at least 5 characters long")
    private String productName;

    @NotNull(message = "Product description is required")
    @Size(min = '5', message = "Product description must be at least 5 characters long")
    private String productDescription;

    @Min(value = 1, message = "Price must be at least 1")
    private Double price;

//    @ManyToOne
//    @JsonIgnore
//    private User seller;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    @NotNull
    private Category category;

    public Product() {
    }

    public Product(Integer productId, String productName, String productDescription, Double price, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}

