package com.springboot.blogAPI.service;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy);

    PostDto getOnePost(Long id);

    PostDto updatePost(PostDto postDto, Long id);

    PostDto updatePostByLike(DoubleIdObjectDto doubleId);

    String deletePost(Long id);
}
