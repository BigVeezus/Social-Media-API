package com.springboot.blogAPI.service.impl;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;
import com.springboot.blogAPI.repository.PostRepository;
import com.springboot.blogAPI.repository.UserRepository;
import com.springboot.blogAPI.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public List<UserDto> getAllFollowers() {
        return null;
    }

    @Override
    public UserDto getOneUser(Long id) {
        return null;
    }

    @Override
    public String followUser(DoubleIdObjectDto doubleId) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }
}
