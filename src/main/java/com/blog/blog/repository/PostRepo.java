package com.blog.blog.repository;

import com.blog.blog.entity.Category;
import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.request.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    @Query(value = "SELECT * FROM post p WHERE p.POST_TITLE LIKE %:keyword%", nativeQuery = true)
    List<Post> findByTitle(String  keyword);
}
