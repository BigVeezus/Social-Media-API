package com.springboot.blogAPI.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String username;
    private String email;
    private String body;
}
