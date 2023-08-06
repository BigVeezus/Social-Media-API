package com.springboot.blogAPI.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PostDto {
    private String title;
    private String content;

    List<String> likes = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;


}
