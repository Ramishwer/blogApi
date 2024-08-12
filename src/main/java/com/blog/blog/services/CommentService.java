package com.blog.blog.services;

import com.blog.blog.request.CommentRequest;

import java.util.List;

public interface CommentService {

    CommentRequest createComment(CommentRequest commentRequest,Integer postId);

    void deleteByComment(Integer commentId);


}
