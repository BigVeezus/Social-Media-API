package com.springboot.blogAPI.service;

import com.springboot.blogAPI.dto.LoginDto;
import com.springboot.blogAPI.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
