package com.blog.blog.security;

import com.blog.blog.exceptions.GeneralException;
import com.blog.blog.repository.UserRepo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo uRepo;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException {

        log.info("===: CustomUserDetailService:: Inside loadUserByUsername Method :===");


        log.info("userName = " + userName);

        return (UserDetails) this.uRepo.findByEmail(userName).orElseThrow(() -> new GeneralException("User not " +
                "found With userEmail = " + userName));


    }
}