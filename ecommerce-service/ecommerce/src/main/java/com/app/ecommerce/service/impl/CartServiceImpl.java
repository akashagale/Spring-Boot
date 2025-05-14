package com.app.ecommerce.service.impl;

import com.app.ecommerce.converter.Converter;
import com.app.ecommerce.dto.CartDto;
<<<<<<< HEAD
import com.app.ecommerce.dto.CartProductDto;
import com.app.ecommerce.dto.PostCartDto;
import com.app.ecommerce.entity.*;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.repo.*;
import com.app.ecommerce.service.CartService;
import com.app.ecommerce.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
=======
import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.repo.CartRepo;
import com.app.ecommerce.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
>>>>>>> 416287238779966ee38873478f3f428b06de9974

@Service
public class CartServiceImpl implements CartService {

<<<<<<< HEAD
    Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepo cartRepo;
    private final CartProductRepo cartProductRepo;
    private final JwtUtils jwtUtils;
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
//    private final UserRepository userRepo;


    public CartServiceImpl(CartRepo cartRepo, CartProductRepo cartProductRepo, JwtUtils jwtUtils, ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
        this.jwtUtils = jwtUtils;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public CartDto getCartProductItems() {

        Integer userId = this.jwtUtils.getLoggedInUsername();

        Optional<Cart> optionalCart = this.cartRepo.findById(userId);
        logger.info("CartServiceImpl inside getCart ");
        if (optionalCart.isEmpty()) return null;
        return Converter.OptionalCartToCartDto(optionalCart);
    }

    @Override
    public void postCart(Integer productId, PostCartDto cartDto) {

        User loggedInUser = jwtUtils.getLoggedInUser();

        Cart cart = cartRepo.findCartByUserId(loggedInUser.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(loggedInUser);
                    newCart.setTotalAmount(0.0);
                    return cartRepo.save(newCart);
                });

        // Step 1: Get or create the product
        Product product = productRepo.findById(productId)
                .orElseGet(() -> {
                    // Also get or create category
                    Category category = categoryRepo.findById(cartDto.getCategory().getCategoryId())
                            .orElseGet(() -> {
                                Category newCat = new Category();
                                newCat.setCategoryName(cartDto.getCategory().getCategoryName());
                                return categoryRepo.save(newCat);
                            });

                    Product newProduct = new Product();
//                    newProduct.setProductId(productId); // Optional if auto-generated
                    newProduct.setProductName(cartDto.getProductName());
                    newProduct.setProductDescription(cartDto.getProductName());
                    newProduct.setPrice(cartDto.getPrice());
                    newProduct.setCategory(category);
                    return productRepo.save(newProduct);
                });

        // Step 2: Check if already in cart
        boolean alreadyInCart = cartProductRepo.existsByCartAndProduct(cart, product);
        if (alreadyInCart) {
            throw new ECommerceException("Product already exists in cart"); // Handle with 409 in controller
        }

        // Step 3: Add to cart
        CartProduct cp = new CartProduct();
        cp.setCart(cart);
        cp.setProduct(product);
        cp.setQuantity(1);
        cartProductRepo.save(cp);

        cart.setTotalAmount(cart.getTotalAmount() + product.getPrice());
        cartRepo.save(cart);
=======
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
>>>>>>> 416287238779966ee38873478f3f428b06de9974
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
