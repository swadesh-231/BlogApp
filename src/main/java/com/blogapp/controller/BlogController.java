package com.blogapp.controller;

import com.blogapp.entity.Blog;
import com.blogapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;
    @GetMapping
    public ResponseEntity<List<?>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
    @PostMapping
    public ResponseEntity<?> addBlog(@RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.createBlog(blog));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable ObjectId id) {
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable ObjectId id, @RequestBody Blog blog) {
        return ResponseEntity.ok(blogService.updateBlog(id, blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable ObjectId id) {
        blogService.deleteBlog(id);
        return ResponseEntity.noContent().build();
    }
}
