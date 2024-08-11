package com.blog.blog.serviceImpl;

import com.blog.blog.entity.Category;
import com.blog.blog.entity.Post;
import com.blog.blog.entity.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.CategoryRepo;
import com.blog.blog.repository.PostRepo;
import com.blog.blog.repository.UserRepo;
import com.blog.blog.request.PostDto;
import com.blog.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;


    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

       User user=userRepo.findById(userId)
               .orElseThrow(()->new ResourceNotFoundException("User"," User Id ", userId));

        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ", categoryId));

        Post post=this.modelMapper.map(postDto,Post.class);

        post.setCreatedDate(new Date());
        post.setImageName("img.jpg");
        post.setUser(user);
        post.setCategory(category);

        Post newPost=postRepo.save(post);

        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId) {
        Post post=postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("post"," post Id ", postId));

        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());
        post.setImageName(postDto.getImageName());

        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> list=postRepo.findAll();

        List<PostDto> postDtos=list.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post=postRepo.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post"," post Id ", postId));


        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto deleteByPostId(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPostByCategory(Integer categoryId) {

        Category category=categoryRepo.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category"," Category Id ", categoryId));

       List<Post> posts= postRepo.findByCategory(category);

       List<PostDto> postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostByUserName(Integer userId) {

        User user=userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user"," userId Id ", userId));

        List<Post> posts= postRepo.findByUser(user);

        List<PostDto> postDtos=posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());

        return postDtos;

    }
}
