package com.springboot.blogAPI.controller;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;
import com.springboot.blogAPI.utils.AppConstants;
import com.springboot.blogAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  UserService userService;

    public UserController(UserService userService) {
    this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody DoubleIdObjectDto doubleId){
        return new ResponseEntity<>(userService.followUser(doubleId), HttpStatus.OK);

    }


}
