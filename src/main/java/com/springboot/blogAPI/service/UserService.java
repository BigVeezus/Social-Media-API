package com.springboot.blogAPI.service;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    List<UserDto> getAllFollowers();

    UserDto getOneUser(Long id);

    String followUser(DoubleIdObjectDto doubleId);

    String deleteUser(Long id);
}
