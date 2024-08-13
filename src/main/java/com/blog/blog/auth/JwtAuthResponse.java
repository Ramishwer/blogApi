package com.blog.blog.auth;

import com.blog.blog.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtAuthResponse extends Response {
    private String token;
}

