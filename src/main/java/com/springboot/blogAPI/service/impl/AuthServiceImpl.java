package com.springboot.blogAPI.service.impl;


import com.springboot.blogAPI.model.User;
import com.springboot.blogAPI.errorExceptions.BlogAPIException;
import com.springboot.blogAPI.dto.LoginDto;
import com.springboot.blogAPI.dto.RegisterDto;
import com.springboot.blogAPI.repository.UserRepository;
//import com.springboot.blogAPI.security.JwtTokenProvider;
import com.springboot.blogAPI.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import com.springboot.blogAPI.dto.LoginDto;
import com.springboot.blogAPI.dto.RegisterDto;
import com.springboot.blogAPI.repository.UserRepository;
import com.springboot.blogAPI.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    public AuthServiceImpl (AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    ;}


    @Override
    public String login(LoginDto loginDto) {

      Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),loginDto.getPassword()));
//
//      SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {
        return null;
    }
}
