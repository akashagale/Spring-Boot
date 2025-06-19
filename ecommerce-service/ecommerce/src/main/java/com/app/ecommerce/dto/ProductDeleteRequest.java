package com.app.ecommerce.dto;

public class ProductDeleteRequest {
    private ProductDeleteDto product;
    private int quantity;

    public ProductDeleteDto getProduct() {
        return product;
    }

    public void setProduct(ProductDeleteDto product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}