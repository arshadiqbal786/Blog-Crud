package com.example.blog_app.service;

import com.example.blog_app.dao.CommentDAO;
import com.example.blog_app.dao.PostDAO;
import com.example.blog_app.model.Comment;
import com.example.blog_app.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private CommentDAO commentDAO;
    //addComment()
    public Comment addComment(Comment comment,Long postId){
        Post post = postDAO.findById(postId).orElseThrow(() -> new RuntimeException(postId + "This post id doesn't exists"));
        comment.setPost(post);
        return  commentDAO.save(comment);

    }
    //getCommentByCommentId()

    public Comment getCommentByCommentId(Long id){
        return commentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "This post id doesn't exists"));


    }
    //getCommentByPostId()
    public List<Comment> getCommentByPostId(Long postId){
        return commentDAO.findByPostId(postId);

    }
    //updateCommentById()
    public void updateCommentById(Long commentId, Long postId, Comment comment){
        Post post = postDAO.findById(postId).orElseThrow(() -> new RuntimeException(postId + "This post id doesn't exists"));
        comment.setId(commentId);
        comment.setPost(post);
        commentDAO.save(comment);

    }
    //deleteComment()
    public void deleteCommentByCommentId(Long id){
        if(commentDAO.findById(id).isPresent()){
            commentDAO.deleteById(id);

        }else {
            throw  new RuntimeException(id +"This id doesn't exists");
        }
    }
}
