package com.springboot.blogAPI.dto;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String profilePic;

    private String password;

    List<String> following = new ArrayList<>();
    List<String> followers = new ArrayList<>();
}
