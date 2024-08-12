package com.blog.blog.controller;

import com.blog.blog.constants.ApiConstant;
import com.blog.blog.request.CategoryDto;
import com.blog.blog.request.PostDto;
import com.blog.blog.response.PostResponse;
import com.blog.blog.services.FileService;
import com.blog.blog.services.PostService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
    public ResponseEntity<PostResponse> getALlPosts(
            @RequestParam(value = "pageNumber",defaultValue = ApiConstant.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue=ApiConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = ApiConstant.SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = ApiConstant.SORT_DIR,required = false) String sortDir
    ){
        PostResponse postDtoList= postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);

        return new ResponseEntity<PostResponse>(postDtoList,HttpStatus.OK);

    }

    @GetMapping("/getallposts")
    public ResponseEntity<List<PostDto>> getAllPostWithoutPagination(){
        List<PostDto> postDtoList= postService.getAllPostWithoutPagination();

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

    @GetMapping("/post/search/{keywords}")
    public ResponseEntity<List<PostDto>> search( @PathVariable String keywords){
        List<PostDto> listPost=postService.getTitleContainingKeyword(keywords);
        return new ResponseEntity<List<PostDto>>(listPost, HttpStatus.OK);

    }


}
