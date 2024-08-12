package com.blog.blog.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private Integer categoryId;

    @NotBlank(message = "Category Title is mandatory")
    private String categoryTitle;

    private String categoryDescription;


}
