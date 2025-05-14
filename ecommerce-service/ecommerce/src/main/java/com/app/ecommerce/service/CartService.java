package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartDto;
import com.app.ecommerce.dto.CartProductDto;
import com.app.ecommerce.dto.PostCartDto;

import java.util.List;

public interface CartService {
    public CartDto getCartProductItems();
    public void postCart(Integer productId,PostCartDto cartDto);
    public CartDto putCart(CartDto cartDto);
    public Object deleteCart(CartDto cartDto);
}
