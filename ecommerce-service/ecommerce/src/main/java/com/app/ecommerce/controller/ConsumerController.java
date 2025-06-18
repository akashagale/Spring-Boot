package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartDto;
import com.app.ecommerce.dto.PostCartDto;
import com.app.ecommerce.dto.PutCartDto;
import com.app.ecommerce.exception.ECommerceException;
import com.app.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

import static ch.qos.logback.core.CoreConstants.SYSOUT;

@RestController
@RequestMapping("api/auth/consumer")
public class ConsumerController {

    private final Logger logger = Logger.getLogger(ConsumerController.class.getName());

    private final CartService cartService;
    public ConsumerController(CartService cartService) {
        this.cartService = cartService;
    }

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
            cartService.postCart(cartDto.getProductId(), cartDto);
            return ResponseEntity.ok("Product added to cart successfully");
        } catch (ECommerceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Product is already in the cart");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding product to cart");
        }
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @PutMapping("/putcart")
    public ResponseEntity<?> putCart (@RequestBody PutCartDto putCartDto) {
        CartDto cartDto = this.cartService.putCart(putCartDto);
        if (cartDto != null) return ResponseEntity.status(HttpStatus.OK).body(cartDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @DeleteMapping("/deletecart")
    public ResponseEntity<Object> deleteCart () {
        return null;
    }
}