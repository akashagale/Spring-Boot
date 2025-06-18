package com.app.ecommerce.dto;

public class PutProductDto {

    private int productId;
    private PutCategoryDto category;
    private double price;
    private String productName;

    public PutProductDto() {
    }

    public PutProductDto(int productId, PutCategoryDto category, double price, String productName) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.productName = productName;
    }

    public PutCategoryDto getCategory() {
        return category;
    }

    public void setCategory(PutCategoryDto category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "product{" +
                "productId=" + productId +
                ", category=" + category +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                '}';
    }
}
