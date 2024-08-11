package com.blog.blog.services;

import com.blog.blog.request.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    List<PostDto> getAllPost();

    PostDto getPostById(Integer postId);

    PostDto deleteByPostId(Integer postId);

    List<PostDto> getAllPostByCategory(Integer categoryId);

    List<PostDto> getAllPostByUserName(Integer userId);

}
