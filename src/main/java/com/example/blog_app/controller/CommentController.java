package com.example.blog_app.controller;

import com.example.blog_app.model.Comment;
import com.example.blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping("/posts/{id}/addComment")
    public ResponseEntity<String> addComment(@PathVariable Long id, @RequestBody Comment comment){
        Comment response = commentService.addComment(comment, id);
        return new ResponseEntity<String>("Comment Posted Successfully. Id ="+ response.getId(), HttpStatus.CREATED);
    }
    @GetMapping("/getComment/{id}")
    public ResponseEntity<Comment> getCommentByCommentId(@PathVariable Long id){

            Comment comment = commentService.getCommentByCommentId(id);
            return new  ResponseEntity<Comment>(comment, HttpStatus.OK);
    }
    @GetMapping("/posts/{id}/getComments")
    public List<Comment> getCommentByPostId(@PathVariable Long id){
       return commentService.getCommentByPostId(id);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> updateCommentById(@PathVariable Long postId,@PathVariable Long commentId,@RequestBody Comment comment){
         commentService.updateCommentById(commentId, postId, comment);
        return  new ResponseEntity<>("Comment Updated Successfully",HttpStatus.OK);
    }
    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<String>deleteComment(@PathVariable Long id){
        commentService.deleteCommentByCommentId(id);
        return new ResponseEntity<>("Comment deleted successfully" ,HttpStatus.OK);
    }

}
