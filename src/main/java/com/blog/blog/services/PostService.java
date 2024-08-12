package com.blog.blog.services;

import com.blog.blog.request.PostDto;
import com.blog.blog.response.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    List<PostDto> getAllPostWithoutPagination();
    PostDto getPostById(Integer postId);

    PostDto deleteByPostId(Integer postId);

    List<PostDto> getAllPostByCategory(Integer categoryId);

    List<PostDto> getAllPostByUserName(Integer userId);

    List<PostDto> getTitleContainingKeyword(String keyword);

}
