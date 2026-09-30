package com.malvin.spring_blog.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.malvin.spring_blog.repositories.CategoryRepository;
import com.malvin.spring_blog.services.CategoryService;

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
}
