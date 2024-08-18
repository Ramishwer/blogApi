package com.blog.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;



//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("===: JwtAuthenticationFilter:: Inside doFilterInternal Method :===");
//
//
//        String requestToken = request.getHeader("Authorization"); // Token is on the form of Bearer 2353454sdg
//        log.info("requestToken = " + requestToken);
//
//
//        String username = null;
//        String token = null;
//
//        if (requestToken != null && requestToken.startsWith("Bearer")) {
//
//            token = requestToken.substring(7);
//
//            try {
//                /*----Now Fetch UserName using JwtTokenHelper Class Method ----*/
//                username = this.jwtTokenHelper.getUsernameFromToken(token);
//            } catch (IllegalArgumentException e) {
//                throw new IllegalArgumentException("Unable to get Jwt token.");
//            } catch (ExpiredJwtException e) {
//                log.error("JWT token has been expired.");
//            } catch (MalformedJwtException e) {
//                throw new MalformedJwtException("Invalid JWT");
//            }
//        }else {
//            log.error("JWT Token doesn't not begin with Bearer.");
//        }
//
//
//        /*---- 2. Once we get the token, Now Validate The Token. ----*/
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//            if (Boolean.TRUE.equals(this.jwtTokenHelper.validateToken(token, userDetails))) {
//
//                // Now Authenticate:
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//            } else {
//                log.error("Invalid JWT Token");
//            }
//        } else {
//            log.error("username is null or context is not null.");
//        }
//
//
//        filterChain.doFilter(request, response);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("===: JwtAuthenticationFilter:: Inside doFilterInternal Method :===");


        String requestToken = request.getHeader("Authorization"); // Token is on the form of Bearer 2353454sdg
        log.info("requestToken = " + requestToken);


        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {

            token = requestToken.substring(7);

            try {
                /*----Now Fetch UserName using JwtTokenHelper Class Method ----*/
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unable to get Jwt token.");
            } catch (ExpiredJwtException e) {
                log.error("JWT token has been expired.");
            } catch (MalformedJwtException e) {
                throw new MalformedJwtException("Invalid JWT");
            }
        }else {
            log.error("JWT Token doesn't not begin with Bearer.");
        }


        /*---- 2. Once we get the token, Now Validate The Token. ----*/
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (Boolean.TRUE.equals(this.jwtTokenHelper.validateToken(token, userDetails))) {

                // Now Authenticate:
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                log.error("Invalid JWT Token");
            }
        } else {
            log.error("username is null or context is not null.");
        }


        filterChain.doFilter(request, response);
    }
}