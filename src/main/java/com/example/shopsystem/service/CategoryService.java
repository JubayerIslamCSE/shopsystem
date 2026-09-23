package com.example.shopsystem.service;

import com.example.shopsystem.model.Category;
import com.example.shopsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }

    public Category findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + id));
    }

    public Category save(Category category) { return categoryRepository.save(category); }

    public void delete(String id) { categoryRepository.deleteById(id); }

    public long count() { return categoryRepository.count(); }
}