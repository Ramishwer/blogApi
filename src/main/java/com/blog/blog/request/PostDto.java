package com.blog.blog.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;

    private String imageName;
    private Date createdDate;

    private CategoryDto category;
    private UserDto user;

}
