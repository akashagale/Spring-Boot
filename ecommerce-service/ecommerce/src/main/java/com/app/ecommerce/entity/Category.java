package com.app.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> productDetails;

    public Category() {
    }

    public Category(Integer categoryId, String categoryName, List<Product> productDetails) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productDetails = productDetails;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<Product> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", productDetails=" + productDetails +
                '}';
    }
}
