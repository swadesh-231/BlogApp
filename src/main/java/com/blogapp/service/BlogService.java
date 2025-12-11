package com.blogapp.service;

import com.blogapp.entity.Blog;
import org.bson.types.ObjectId;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog createBlog(Blog blog);
    Blog findById(ObjectId id);
    Blog updateBlog(ObjectId id, Blog blog);
    void deleteBlog(ObjectId id);
}
