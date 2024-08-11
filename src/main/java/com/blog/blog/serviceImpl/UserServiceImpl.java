package com.blog.blog.serviceImpl;

import com.blog.blog.entity.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.request.UserDto;
import com.blog.blog.repository.UserRepo;
import com.blog.blog.services.UseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UseService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user=this.dtoToUser(userDto);
        User savedUsers=userRepo.save(user);

        return this.userToDto(savedUsers);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," Id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updateUsers= userRepo.save(user);
        return this.userToDto(updateUsers);

    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," Id ", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> list=userRepo.findAll();

        List<UserDto> userDtoList=list.stream().map(this::userToDto).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void deleteByUserId(Integer userId) {
        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User"," Id ", userId));

         userRepo.delete(user);
    }


    public User dtoToUser(UserDto userDto){

        User user=this.modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto=this.modelMapper.map(user,UserDto.class);
//        UserDto userDto=new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        System.out.println("email:"+user.getEmail());

        System.out.println("getAbout:"+user.getAbout());

        return userDto;
    }
}
