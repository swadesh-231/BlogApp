package com.blogapp.service.impl;

import com.blogapp.entity.Blog;
import com.blogapp.repository.BlogRepository;
import com.blogapp.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog findById(ObjectId id) {
        return blogRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Blog not found"));
    }

    @Override
    public Blog updateBlog(ObjectId id, Blog blog) {
        Blog existing = findById(id);
        existing.setTitle(blog.getTitle());
        existing.setContent(blog.getContent());
        return blogRepository.save(existing);
    }

    @Override
    public void deleteBlog(ObjectId id) {
        blogRepository.deleteById(id);
    }
}
