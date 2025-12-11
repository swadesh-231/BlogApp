package com.blogapp.repository;

import com.blogapp.entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BlogRepository extends MongoRepository<Blog, ObjectId> {
}
