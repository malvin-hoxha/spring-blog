package com.malvin.spring_blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import com.malvin.spring_blog.repositories.CategoryRepository;
import com.malvin.spring_blog.services.CategoryService;

import jakarta.transaction.Transactional;

import com.malvin.spring_blog.domain.entities.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if (categoryRepository.existsByNameIgnoreCase(categoryName)) {
            throw new IllegalArgumentException("Category with name '" + categoryName + "' already exists.");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            if (category.get().getPosts().size() > 0) {
                throw new IllegalArgumentException("Category has associated posts and cannot be deleted.");
            }
            categoryRepository.deleteById(id);
        }
    }
}
