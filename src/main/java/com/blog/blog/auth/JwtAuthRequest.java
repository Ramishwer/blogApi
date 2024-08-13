package com.blog.blog.auth;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;
    private String password;
}

