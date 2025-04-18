package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartDto;

import java.util.List;

public interface CartService {
    public List<CartDto> getCart();
    public CartDto postCart(CartDto cartDto);
    public CartDto putCart(CartDto cartDto);
    public Object deleteCart(CartDto cartDto);

}
