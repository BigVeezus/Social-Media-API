package com.springboot.blogAPI.controller;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.UserDto;
import com.springboot.blogAPI.model.User;
import com.springboot.blogAPI.utils.AppConstants;
import com.springboot.blogAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  UserService userService;

    public UserController(UserService userService) {
    this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long Id){
        return new ResponseEntity<>(userService.getOneUser(Id), HttpStatus.OK);
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestBody DoubleIdObjectDto doubleId){
        return new ResponseEntity<>(userService.followUser(doubleId), HttpStatus.OK);
    }

    @PostMapping("/Unfollow")
    public ResponseEntity<String> unfollowUser(@RequestBody DoubleIdObjectDto doubleId){
        return new ResponseEntity<>(userService.unfollowUser(doubleId), HttpStatus.OK);
    }

    @GetMapping("/getfollowing/{Id}")
    public ResponseEntity<List<User>> getfollowing(@PathVariable Long Id){
        return new ResponseEntity<>(userService.getAllFollowing(Id), HttpStatus.OK);
    }

    @GetMapping("/getfollowers/{Id}")
    public ResponseEntity<List<User>> getfollowers(@PathVariable Long Id){
        return new ResponseEntity<>(userService.getAllFollowers(Id), HttpStatus.OK);
    }


}
