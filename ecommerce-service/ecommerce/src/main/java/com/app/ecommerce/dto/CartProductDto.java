package com.app.ecommerce.dto;

public class CartProductDto {

    private Integer cpId;

    private CartDto cartDto;

    private ProductDto productDto;

    private Integer quantity = 1;

    public CartProductDto() {
    }

    public CartProductDto(Integer cpId, CartDto cartDto, ProductDto productDto, Integer quantity) {
        this.cpId = cpId;
        this.cartDto = cartDto;
        this.productDto = productDto;
        this.quantity = quantity;
    }

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public CartDto getCartDto() {
        return cartDto;
    }

    public void setCartDto(CartDto cartDto) {
        this.cartDto = cartDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartProductDto{" +
                "cpId=" + cpId +
                ", cartDto=" + cartDto +
                ", productDto=" + productDto +
                ", quantity=" + quantity +
                '}';
    }

}
