package com.blog.blog.controller;


import com.blog.blog.auth.JwtAuthRequest;
import com.blog.blog.exceptions.GeneralException;
import com.blog.blog.request.UserDto;
import com.blog.blog.response.Response;
import com.blog.blog.util.BlogService;
import com.blog.blog.util.BlogServiceFactory;
import com.blog.blog.util.BlogServiceType;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@Slf4j
public class AuthController {

    @Autowired
    private BlogServiceFactory factory;


    @PostMapping("/login")
    public ResponseEntity<Response> createToken(@RequestBody @Valid JwtAuthRequest request) throws GeneralException {
        log.info("===: AuthController:: Inside createToken Method :===");
        BlogService service = factory.getService(BlogServiceType.LOGIN);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody @Valid UserDto request) throws GeneralException {
        log.info("===: AuthController:: Inside registerUser Method :===");
        BlogService service = factory.getService(BlogServiceType.REGISTER_USER);
        Response response = service.executeService(request, "");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
