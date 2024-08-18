package com.blog.blog.security;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


//    @Override
//    public void commence (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//
//
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!");
//    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied!!!");
    }
}
