package com.blog.blog.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogResponse {

    private String status;
    private String statusCode;
    private String message;
    private transient Object data;
}
