package com.example.shopsystem.repository;

import com.example.shopsystem.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}