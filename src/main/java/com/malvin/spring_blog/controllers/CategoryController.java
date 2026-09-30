package com.malvin.spring_blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malvin.spring_blog.services.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import com.malvin.spring_blog.domain.dtos.CategoryDto;
import com.malvin.spring_blog.mappers.CategoryMapper;

@RestController 
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor 
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.ok(categories);
    }
    
}
