package com.blog.blog.controller;

import com.blog.blog.request.CommentRequest;
import com.blog.blog.response.ApiResponse;
import com.blog.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentRequest> createComment(@RequestBody CommentRequest commentRequest,@PathVariable Integer postId){
        CommentRequest commentRequest1=    commentService.createComment(commentRequest,postId);
        return new ResponseEntity<CommentRequest>(commentRequest1, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        commentService.deleteByComment(commentId);
        ApiResponse response = new ApiResponse("Comment deleted successfully", true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
