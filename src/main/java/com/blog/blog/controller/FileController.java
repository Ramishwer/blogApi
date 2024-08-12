package com.blog.blog.controller;

import com.blog.blog.request.PostDto;
import com.blog.blog.services.FileService;
import com.blog.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/post")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PostService postService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostDto> postImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId) throws IOException {
        String fileName=fileService.uploadImage(path,image);
        PostDto postDto=postService.getPostById(postId);
        postDto.setImageName(fileName);

        PostDto postDto1=postService.updatePost(postDto,postId);

        return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);


    }

    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public  void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=fileService.getSource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
