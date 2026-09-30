package com.malvin.spring_blog.services;

import java.util.List;

import com.malvin.spring_blog.domain.entities.Category;

public interface CategoryService {
    List<Category> listCategories();
}
