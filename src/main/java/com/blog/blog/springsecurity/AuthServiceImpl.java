package com.blog.blog.springsecurity;

import com.blog.blog.entity.User;
import com.blog.blog.repository.UserRepo;
import com.blog.blog.request.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

	 @Autowired
	 private UserRepo userRepository;
	 
	 
	  public UserDto createUser(SignupRequest signupRequest) {
	        User user = new User();
	        user.setName(signupRequest.getName());
	        user.setEmail(signupRequest.getEmail());
	        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
	        User createdUser = userRepository.save(user);
	        UserDto userDTO = new UserDto();
	        userDTO.setEmail(createdUser.getEmail());
	        userDTO.setName(createdUser.getName());
	        return userDTO;
	    }
}
