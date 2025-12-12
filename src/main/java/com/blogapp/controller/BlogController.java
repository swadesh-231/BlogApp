package com.blogapp.controller;

import com.blogapp.entity.Blog;
import com.blogapp.service.BlogService;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/{username}")
    public ResponseEntity<Blog> createBlog(
            @PathVariable String username,
            @RequestBody Blog blog) {

        Blog created = blogService.createBlog(blog, username);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<Blog>> getBlogsByUser(@PathVariable String username) {
        List<Blog> blogs = blogService.getBlogsByUser(username);
        return blogs.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable ObjectId id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PutMapping("/{username}/{id}")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable String username,
            @PathVariable ObjectId id,
            @RequestBody Blog blog) {

        return ResponseEntity.ok(blogService.updateBlog(id, blog, username));
    }

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Void> deleteBlog(
            @PathVariable String username,
            @PathVariable ObjectId id) {

        blogService.deleteBlog(id, username);
        return ResponseEntity.noContent().build();
    }
}
