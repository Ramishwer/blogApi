package com.blog.blog.springsecurity;

import lombok.Data;

@Data
public class SignupRequest {

	private String name;

    private String email;

    private String password;
    
    private String phone;

	
}
