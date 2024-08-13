package com.blog.blog.security;

import com.blog.blog.constants.JwtSecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component

public class JwtTokenHelper {

    public static final long JWT_TOKEN_VALIDITY=5*60;

    private String secret="afestytfiuggghouigyfytftufyufiygiygiuhiuhhigiygiygiyg";

    public String getUsernameFromToken(String token) {

        return getClaimFromToken(token, Claims::getSubject);
    }

    /*----Retrieve Expiration date from jwt token:----*/
    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    /*----For Retrieving any information from token we will need the secret key:----*/
    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(JwtSecurityConstants.SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /*----Check if the token has expired:----*/
    private Boolean isTokenExpired(String token) {

        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    /*----Generate token for user:----*/
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }



    private String doGenerateToken (Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + JwtSecurityConstants.JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, JwtSecurityConstants.SECRET_KEY).compact();

    }


    /*----Validate Token:----*/
    public Boolean validateToken(String token, UserDetails userDetails) {

        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
