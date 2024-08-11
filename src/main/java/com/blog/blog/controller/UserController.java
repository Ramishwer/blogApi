package com.blog.blog.controller;

import com.blog.blog.request.UserDto;
import com.blog.blog.response.ApiResponse;
import com.blog.blog.serviceImpl.UserServiceImpl;
import com.blog.blog.services.UseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UseService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getAllUserById(@PathVariable Integer userId){

        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>  updateUsers(@Valid @PathVariable Integer userId,@RequestBody UserDto userDto){

        UserDto updateUserDto=userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updateUserDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUsers(@PathVariable Integer userId){

        userService.deleteByUserId(userId);
        ApiResponse response = new ApiResponse("User deleted successfully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
