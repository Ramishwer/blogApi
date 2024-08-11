package com.blog.blog.services;

import com.blog.blog.request.UserDto;

import java.util.List;

public interface UseService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto,Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteByUserId(Integer userId);
}
