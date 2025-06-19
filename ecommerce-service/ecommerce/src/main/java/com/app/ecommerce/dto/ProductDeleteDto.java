package com.app.ecommerce.dto;

public class ProductDeleteDto {

    private Integer productId;
    private CategoryDeleteDto category;
    private String price;
    private String productName;


    public ProductDeleteDto() {
    }

    public ProductDeleteDto(Integer productId, CategoryDeleteDto category, String price, String productName) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public CategoryDeleteDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDeleteDto category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "ProductDeleteDto{" +
                "productId=" + productId +
                ", category=" + category +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                '}';
    }
}
