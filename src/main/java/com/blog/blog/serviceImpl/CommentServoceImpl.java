package com.blog.blog.serviceImpl;

import com.blog.blog.entity.Comment;
import com.blog.blog.entity.Post;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.repository.CommentRepo;
import com.blog.blog.repository.PostRepo;
import com.blog.blog.request.CommentRequest;
import com.blog.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServoceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentRequest createComment(CommentRequest commentRequest,Integer postId) {

        Post post= postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId","id",postId));

       Comment comment=this.modelMapper.map(commentRequest,Comment.class);

       comment.setPost(post);

       Comment savedComment=commentRepo.save(comment);

        return this.modelMapper.map(savedComment,CommentRequest.class);
    }

    @Override
    public void deleteByComment(Integer commentId) {

        Comment comment= commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("commentId","id",commentId));
        commentRepo.delete(comment);
    }
}
