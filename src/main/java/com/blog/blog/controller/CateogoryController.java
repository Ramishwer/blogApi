package com.blog.blog.controller;


import com.blog.blog.request.CategoryDto;
import com.blog.blog.response.ApiResponse;
import com.blog.blog.response.BlogResponse;
import com.blog.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CateogoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory=categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);

    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCateogry(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto upadteCategory=categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<CategoryDto>(upadteCategory, HttpStatus.OK);

    }


    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategoryId(@PathVariable Integer categoryId){
        categoryService.deleteByCategoryId(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("category sucessfully Deleted!!",true),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<BlogResponse> getAllCategory(){
        BlogResponse list= categoryService.getAllCategory();

       return  ResponseEntity.ok(list);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryId(@PathVariable Integer categoryId){
        CategoryDto categoryDto= categoryService.getCategoryById(categoryId);

        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
}
