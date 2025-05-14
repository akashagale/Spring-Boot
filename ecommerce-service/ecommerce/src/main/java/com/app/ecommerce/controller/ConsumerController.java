package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartDto;
<<<<<<< HEAD
import com.app.ecommerce.dto.CartProductDto;
import com.app.ecommerce.dto.PostCartDto;
import com.app.ecommerce.entity.Cart;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.service.CartService;
import com.app.ecommerce.service.impl.CartServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
=======
import com.app.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

>>>>>>> 416287238779966ee38873478f3f428b06de9974
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/auth/consumer")
public class ConsumerController {

    private final Logger logger = Logger.getLogger(ConsumerController.class.getName());

    private final CartService cartService;
<<<<<<< HEAD
    
=======

>>>>>>> 416287238779966ee38873478f3f428b06de9974
    public ConsumerController(CartService cartService) {
        this.cartService = cartService;
    }

<<<<<<< HEAD
    @PreAuthorize("hasRole('CONSUMER')")
    @GetMapping("/cart")
    public ResponseEntity<CartDto> getCart() {
        CartDto cart = this.cartService.getCartProductItems();
        if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
}

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @PostMapping("/postcart")
    public ResponseEntity<?> postCart(@Valid @RequestBody PostCartDto cartDto) {
        try {
            cartService.postCart(cartDto.getProductId(),cartDto);
            return ResponseEntity.ok("Product added to cart successfully");
        } catch (ECommerceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product is already in the cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart");
        }
=======

    @PostAuthorize("hasRole('CONSUMER')")
    @GetMapping("/cart")
    public ResponseEntity<Object> getCart() {
        List<CartDto> cart = this.cartService.getCart();
        if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @PostMapping("/postcart")
    public ResponseEntity<Object> postCart(@RequestBody CartDto cartDto) {
        CartDto updatedCartDto = this.cartService.postCart(cartDto);
        if (updatedCartDto == null) return ResponseEntity.status(HttpStatus.valueOf(409)).body(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCartDto);
>>>>>>> 416287238779966ee38873478f3f428b06de9974
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @PutMapping("/putcart")
<<<<<<< HEAD
    public ResponseEntity<?> putCart() {
=======
    public ResponseEntity<Object> putCart() {
>>>>>>> 416287238779966ee38873478f3f428b06de9974
        return null;
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @DeleteMapping("/deletecart")
    public ResponseEntity<Object> deleteCart() {
        return null;
    }

}
