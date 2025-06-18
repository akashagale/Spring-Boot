package com.app.ecommerce.dto;

public class PutCartDto {

    private PutProductDto product;
    private Integer quantity;

    public PutCartDto() {
    }

    public PutCartDto(PutProductDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public PutProductDto getProduct() {
        return product;
    }

    public void setProduct(PutProductDto product) {
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
        return "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
