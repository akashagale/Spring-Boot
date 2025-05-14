package com.app.ecommerce.dto;

public class PutCartDto {
    private Integer productId;
    private PostCategoryDto category;
    private Double price;
    private String productName;

    public PutCartDto() {
    }

    public PutCartDto(Integer productId, PostCategoryDto category, Double price, String productName) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PostCategoryDto getCategory() {
        return category;
    }

    public void setCategory(PostCategoryDto category) {
        this.category = category;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "PostCartDto{" +
                "productId=" + productId +
                ", category=" + category +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                '}';
    }
}
