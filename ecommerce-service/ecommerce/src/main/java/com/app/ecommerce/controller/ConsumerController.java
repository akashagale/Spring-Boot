package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartDto;
import com.app.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/auth/consumer")
public class ConsumerController {

    private final Logger logger = Logger.getLogger(ConsumerController.class.getName());

    private final CartService cartService;

    public ConsumerController(CartService cartService) {
        this.cartService = cartService;
    }


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
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @PutMapping("/putcart")
    public ResponseEntity<Object> putCart() {
        return null;
    }

    @PreAuthorize("hasRole('ROLE_CONSUMER')")
    @DeleteMapping("/deletecart")
    public ResponseEntity<Object> deleteCart() {
        return null;
    }

}
