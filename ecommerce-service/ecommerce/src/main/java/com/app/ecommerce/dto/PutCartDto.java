package com.app.ecommerce.dto;

public class PutCartDto {

    private ProductPutDTO product;
    private Integer quantity;

    public PutCartDto() {
    }

    public PutCartDto(ProductPutDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductPutDTO getProduct() {
        return product;
    }

    public void setProduct(ProductPutDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PutCartDto{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
