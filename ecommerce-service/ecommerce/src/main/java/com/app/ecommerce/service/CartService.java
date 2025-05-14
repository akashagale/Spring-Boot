package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartDto;
<<<<<<< HEAD
import com.app.ecommerce.dto.CartProductDto;
import com.app.ecommerce.dto.PostCartDto;
=======
>>>>>>> 416287238779966ee38873478f3f428b06de9974

import java.util.List;

public interface CartService {
<<<<<<< HEAD
    public CartDto getCartProductItems();
    public void postCart(Integer productId,PostCartDto cartDto);
    public CartDto putCart(CartDto cartDto);
    public Object deleteCart(CartDto cartDto);
=======
    public List<CartDto> getCart();
    public CartDto postCart(CartDto cartDto);
    public CartDto putCart(CartDto cartDto);
    public Object deleteCart(CartDto cartDto);

>>>>>>> 416287238779966ee38873478f3f428b06de9974
}
