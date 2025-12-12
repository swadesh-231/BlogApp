package com.blogapp.service;

import com.blogapp.entity.Blog;
import org.bson.types.ObjectId;

import java.util.List;

public interface BlogService {
    Blog createBlog(Blog blog, String username);
    Blog findById(ObjectId id);
    Blog updateBlog(ObjectId id, Blog blog, String username);
    void deleteBlog(ObjectId id, String username);
    List<Blog> getBlogsByUser(String username);
}
