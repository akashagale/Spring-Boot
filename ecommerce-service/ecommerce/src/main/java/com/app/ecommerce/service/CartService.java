package com.app.ecommerce.service;

import com.app.ecommerce.dto.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartService {
    public CartDto getCartProductItems();
    public void postCart(Integer productId,PostCartDto cartDto);
    public List<CartDto> getCart();
    public CartDto putCart(PutCartDto cartDto);
    public Object deleteCartRepoByProductId(Integer productId);

}
