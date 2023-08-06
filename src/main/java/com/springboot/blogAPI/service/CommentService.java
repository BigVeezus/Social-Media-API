package com.springboot.blogAPI.service;

import com.springboot.blogAPI.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(long postId);

    void deleteComment(Long postId, Long commentId);
}
