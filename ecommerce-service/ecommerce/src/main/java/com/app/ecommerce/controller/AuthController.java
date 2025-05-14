package com.app.ecommerce.controller;

import com.app.ecommerce.dto.LoginDto;
import com.app.ecommerce.dto.RegisterDto;
import com.app.ecommerce.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final Logger logger = Logger.getLogger(AuthController.class.getName());

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String  token = authService.login(loginDto);
        if (token == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!!");
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    // Build Register REST API
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        logger.info("==================== " + response + " ===================>");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
