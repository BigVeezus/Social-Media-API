package com.springboot.blogAPI.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Long likesCount;
    private Date createdAt;
}
