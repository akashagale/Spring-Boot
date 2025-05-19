package com.app.ecommerce.dto;

import lombok.Getter;


public class ProductPutDTO {

    private Long productId;
    private String productName;
    private double price;
    private CategoryPutDTO category;

    public ProductPutDTO() {
    }

    public ProductPutDTO(Long productId, String productName, double price, CategoryPutDTO category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(CategoryPutDTO category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductPutDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
