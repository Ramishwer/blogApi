package com.blog.blog.serviceImpl;

import com.blog.blog.constants.ApiConstant;
import com.blog.blog.entity.Category;
import com.blog.blog.entity.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.CategoryRepo;
import com.blog.blog.request.CategoryDto;
import com.blog.blog.response.BlogResponse;
import com.blog.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto,Category.class);

        Category savedCatory=categoryRepo.save(category);

        return this.modelMapper.map(savedCatory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ", categoryId));

        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updateCategory=categoryRepo.save(category);

        return this.modelMapper.map(updateCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ", categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public BlogResponse getAllCategory() {

        List<Category> categories =categoryRepo.findAll();

       List<CategoryDto> categoryDtos= categories.stream().map(category -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());

        BlogResponse response=new BlogResponse();
        response.setStatus(ApiConstant.STATUS);
        response.setStatusCode(ApiConstant.STATUS_CODE);
        response.setMessage("Fetch All Category!!");
        response.setData(categoryDtos);

        return response;
    }

    @Override
    public void deleteByCategoryId(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ", categoryId));

        categoryRepo.delete(category);
    }
}
