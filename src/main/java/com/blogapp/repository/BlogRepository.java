package com.blogapp.repository;

import com.blogapp.entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends MongoRepository<Blog, ObjectId> {
}
