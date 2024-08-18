package com.blog.blog.springsecurity;

import com.blog.blog.entity.User;
import com.blog.blog.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepo userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found___",null);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
	       
}
