package com.app.ecommerce.service.impl;

import com.app.ecommerce.converter.Converter;
import com.app.ecommerce.dto.CartDto;
import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.repo.CartRepo;
import com.app.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepo cartRepo;

    public CartServiceImpl(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    @Override
    public List<CartDto> getCart() {
        List<Cart> cartList = this.cartRepo.findAll();
        if (cartList.isEmpty()) return null;
        return Converter.CartListToCartDtoList(cartList);
    }

    @Override
    public CartDto postCart(CartDto cartDto) {
        boolean existsById = this.cartRepo.existsById(cartDto.getCartId());
        if (existsById) return null;
        Cart savedCart = this.cartRepo.save(Converter.CartDtoToCart(cartDto));
        return savedCart == null ? null : Converter.CartToCartDto(savedCart);
    }

    @Override
    public CartDto putCart(CartDto cartDto) {
        if (cartDto.getCartId() == null) return null;

        boolean existsById = this.cartRepo.existsById(cartDto.getCartId());
        if (!existsById) return null;
        Cart updatedCart = this.cartRepo.save(Converter.CartDtoToCart(cartDto));
        return updatedCart == null ? null : Converter.CartToCartDto(updatedCart);
    }

    @Override
    public Object deleteCart(CartDto cartDto) {
        boolean existsById = this.cartRepo.existsById(cartDto.getCartId());
        if (!existsById) return null;
        this.cartRepo.deleteById(cartDto.getCartId());
        return "User {} deleted successfully! ".formatted(cartDto.getCartId());
    }
}
