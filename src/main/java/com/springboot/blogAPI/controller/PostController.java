package com.springboot.blogAPI.controller;

import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.PostDto;
import com.springboot.blogAPI.model.Post;
import com.springboot.blogAPI.service.PostService;
import com.springboot.blogAPI.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //Create Blog Posts
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false ) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ){
        return new ResponseEntity<>(postService.getAllPosts(pageNo, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getOnePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.getOnePost(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id, @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.updatePost(postDto,id), HttpStatus.OK);
    }

    @PostMapping("likePost")
    public ResponseEntity<String> likeOrUnlikePost(@RequestBody DoubleIdObjectDto doubleId){
        return new ResponseEntity<>(postService.updatePostByLike(doubleId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }
}
