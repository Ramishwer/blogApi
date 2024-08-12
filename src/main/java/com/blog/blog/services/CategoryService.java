package com.blog.blog.services;

import com.blog.blog.request.CategoryDto;
import com.blog.blog.request.UserDto;
import com.blog.blog.response.BlogResponse;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    CategoryDto getCategoryById(Integer categoryId);

    BlogResponse getAllCategory();

    void deleteByCategoryId(Integer categoryId);
}
