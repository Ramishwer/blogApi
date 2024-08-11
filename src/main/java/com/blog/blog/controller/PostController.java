package com.blog.blog.controller;

import com.blog.blog.request.CategoryDto;
import com.blog.blog.request.PostDto;
import com.blog.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
      PostDto postDto1=  postService.createPost(postDto,userId,categoryId);

      return new ResponseEntity<PostDto>(postDto1, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCateogry(@PathVariable Integer categoryId){
        List<PostDto> postDtoList= postService.getAllPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUsername(@PathVariable Integer userId){
        List<PostDto> postDtoList= postService.getAllPostByUserName(userId);

        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);

    }

    @GetMapping("/getAllposts")
    public ResponseEntity<List<PostDto>> getALlPosts(){
        List<PostDto> postDtoList= postService.getAllPost();

        return new ResponseEntity<List<PostDto>>(postDtoList,HttpStatus.OK);

    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDtoList= postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postDtoList,HttpStatus.OK);

    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updateCateogry(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){
        PostDto upadtePost=postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(upadtePost, HttpStatus.OK);

    }
}
