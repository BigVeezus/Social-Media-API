package com.springboot.blogAPI.service;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;
import com.springboot.blogAPI.model.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    List<User> getAllFollowers(Long Id);

    List<User> getAllFollowing(Long Id);


    UserDto getOneUser(Long id);

    String followUser(DoubleIdObjectDto doubleId);

    String unfollowUser(DoubleIdObjectDto doubleId);

    String deleteUser(Long id);
}
