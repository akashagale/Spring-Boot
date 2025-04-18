package com.app.ecommerce.service;

import com.app.ecommerce.dto.LoginDto;
import com.app.ecommerce.dto.RegisterDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
