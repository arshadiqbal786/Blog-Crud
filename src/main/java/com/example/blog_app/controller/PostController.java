package com.example.blog_app.controller;

import com.example.blog_app.model.Post;
import com.example.blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody Post post){
       Post response=postService.createPost(post);
       return new ResponseEntity<>("Post Created Successfully. Id ="+ response.getId(), HttpStatus.CREATED);
    }
    @GetMapping("/getPost/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        try {
            Post response = postService.getPostById(id);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Catch the RuntimeException and return a 404 Not Found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with ID " + id + " not found");
        }
    }
    @GetMapping("/getPost")
    public List<Post>getAllPost(){
        return postService.getAllPost();

    }
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<String>updatePost(@RequestBody Post post, @PathVariable Long id){
        postService.updatePostById(post, id);
        return new ResponseEntity<>("Post Updated Successfully", HttpStatus.OK);
    }
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String>deletePost(@PathVariable Long id){
        postService.deletePostById((id));
        return new ResponseEntity<>("post deleted successfully" ,HttpStatus.OK);
    }

}

