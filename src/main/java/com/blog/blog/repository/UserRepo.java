package com.blog.blog.repository;

import com.blog.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
  User findByEmail (String userEmail);

}
