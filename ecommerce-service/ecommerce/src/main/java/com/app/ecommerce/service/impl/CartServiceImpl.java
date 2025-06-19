package com.app.ecommerce.service.impl;

import com.app.ecommerce.converter.Converter;
import com.app.ecommerce.dto.CartDto;
import com.app.ecommerce.dto.PostCartDto;
import com.app.ecommerce.dto.ProductDeleteDto;
import com.app.ecommerce.dto.PutCartDto;
import com.app.ecommerce.entity.*;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.repo.*;
import com.app.ecommerce.service.CartService;
import com.app.ecommerce.utils.JwtUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.repo.CartRepo;
import com.app.ecommerce.service.CartService;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    Logger logger= LoggerFactory.getLogger(CartServiceImpl.class);

    @PersistenceContext
    EntityManager entityManager;

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

    }
    @Override
    public List<CartDto> getCart() {
        List<Cart> cartList = this.cartRepo.findAll();
        if (cartList.isEmpty()) return null;
        return Converter.CartListToCartDtoList(cartList);
    }

    @Override
    public CartDto putCart(PutCartDto cartDto) {

        Integer loggedInId = jwtUtils.getLoggedInUsername();

        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("put_cart_product")
                .registerStoredProcedureParameter("in_product_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_product_name", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_user_id", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("in_quantity", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("flag_product_found", Boolean.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("deleted", Boolean.class, ParameterMode.OUT);

        query.setParameter("in_product_id", cartDto.getProduct().getProductId());
        query.setParameter("in_product_name", cartDto.getProduct().getProductName());
        query.setParameter("in_user_id", loggedInId);
        query.setParameter("in_quantity", cartDto.getQuantity());
        query.execute();

        boolean flagProductFound = (boolean) query.getOutputParameterValue("flag_product_found");
        boolean deleted = (boolean) query.getOutputParameterValue("deleted");

        if (flagProductFound) {
            System.out.println("Product quantity updated");
            return new CartDto(cartDto.getProduct().getProductId(),cartDto.getProduct().getPrice(),null);
        }else {
            System.out.println("Product not found");
        }

        if (deleted) {
            System.out.println("Product deleted");
        }

        return null;
    }

    @Override
    public Object deleteCartRepoByProductId(Integer productId) {
        Integer existsById = this.cartProductRepo.existsCartProductByProductId(productId);
        if (existsById == 0) return null;

        this.cartProductRepo.deleteCartRepoByProductId(productId);
        return "User {} deleted successfully! ".formatted(productId);
    }
}
