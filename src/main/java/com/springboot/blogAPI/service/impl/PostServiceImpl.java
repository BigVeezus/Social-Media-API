package com.springboot.blogAPI.service.impl;

import java.util.ArrayList;
import com.springboot.blogAPI.dto.DoubleIdObjectDto;
import com.springboot.blogAPI.dto.PostDto;
import com.springboot.blogAPI.errorExceptions.ResourceNotFoundException;
import com.springboot.blogAPI.model.Post;
import com.springboot.blogAPI.repository.PostRepository;
import com.springboot.blogAPI.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    private PostDto convertToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());

        return postDto;
    }

    private Post convertToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        return post;
    }




    @Override
    public Post createPost(PostDto postDto) {

        //convert DTO to entity
        Post post = convertToEntity(postDto);
//        System.out.println(postDto);
        System.out.println(post);
        // Save new post
        Post newPost = postRepository.save(post);

        //convert entity to PostDTO

        return newPost;

    }


    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy) {

        //create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts = postRepository.findAll(pageable);

        //get content from page object
        List<Post> listOfPosts = posts.getContent();
        return listOfPosts.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public PostDto getOnePost(Long id) {

         Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
         System.out.println(post);
        return convertToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return convertToDTO(updatedPost);
    }

    @Override
    public String updatePostByLike(DoubleIdObjectDto doubleId) {
        // id 1 - post Id, id 2 - user who liked post
        Post post = postRepository.findById(Long.valueOf(doubleId.getId1())).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", Long.valueOf(doubleId.getId1())));
        Post targetPost = post;
        List<String> loveList = targetPost.getLikes();
        if (loveList == null) {

            loveList = new ArrayList<>();
        }
        System.out.println(loveList);
        // love and unlove a post
        if (!loveList.contains(doubleId.getId2())) {
            loveList.add(doubleId.getId2());
            System.out.println("Post was liked by user");

        } else {
            System.out.println("No likes found");
            loveList.remove(doubleId.getId2());

        }
        targetPost.setLikes(loveList);
        postRepository.save(targetPost);
        return "success";

    }

    @Override
    public String deletePost(Long id) {

       Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepository.delete(post);

        return "Deleted Post Successfully";
    }


}
