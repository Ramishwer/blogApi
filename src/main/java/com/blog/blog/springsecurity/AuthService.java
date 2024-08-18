package com.blog.blog.springsecurity;

import com.blog.blog.request.UserDto;

public interface AuthService {
	
	UserDto createUser(SignupRequest signupRequest);

}
