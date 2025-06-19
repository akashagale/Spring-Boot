package com.app.ecommerce.dto;


public class ProductPutDto {

    private Long productId;
    private String productName;
    private double price;
    private CategoryDeleteDto category;

    public ProductPutDto() {
    }

    public ProductPutDto(Long productId, String productName, double price, CategoryDeleteDto category) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryDeleteDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDeleteDto category) {
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
