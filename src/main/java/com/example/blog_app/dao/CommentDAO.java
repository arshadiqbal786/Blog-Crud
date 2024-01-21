package com.example.blog_app.dao;

import com.example.blog_app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Long> {
 public List<Comment>findByPostId(Long postId);
}
