package com.springboot.blogAPI.repository;

import com.springboot.blogAPI.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
