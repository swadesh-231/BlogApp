package com.blogapp.service.impl;

import com.blogapp.entity.Blog;
import com.blogapp.entity.User;
import com.blogapp.repository.BlogRepository;
import com.blogapp.service.BlogService;
import com.blogapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Blog createBlog(Blog blog, String username) {
        User user = userService.findByUserName(username.toLowerCase());

        blog.setOwner(user);
        Blog savedBlog = blogRepository.save(blog);

        user.getBlogs().add(savedBlog);
        userService.saveUser(user);

        return savedBlog;
    }

    @Override
    public Blog findById(ObjectId id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    @Transactional
    public Blog updateBlog(ObjectId id, Blog blog, String username) {
        Blog existing = findById(id);

        if (!existing.getOwner().getUsername().equalsIgnoreCase(username)) {
            throw new RuntimeException("Unauthorized: Not the owner");
        }

        existing.setTitle(blog.getTitle());
        existing.setContent(blog.getContent());

        return blogRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteBlog(ObjectId id, String username) {
        Blog existing = findById(id);
        if (!existing.getOwner().getUsername().equalsIgnoreCase(username)) {
            throw new RuntimeException("Unauthorized: Not the owner");
        }
        User owner = existing.getOwner();
        owner.getBlogs().removeIf(b -> b.getId().equals(id));
        userService.saveUser(owner);
        blogRepository.delete(existing);
    }

    @Override
    public List<Blog> getBlogsByUser(String username) {
        User user = userService.findByUserName(username.toLowerCase());
        return user.getBlogs();
    }
}
