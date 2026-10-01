package com.malvin.spring_blog.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.malvin.spring_blog.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.malvin.spring_blog.domain.dtos.ApiErrorResponse;
import com.malvin.spring_blog.domain.dtos.CategoryDto;
import com.malvin.spring_blog.domain.dtos.CreateCategoryRequest;
import com.malvin.spring_blog.domain.entities.Category;
import com.malvin.spring_blog.mappers.CategoryMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        Category categoryToCreate = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(
                categoryMapper.toDto(savedCategory),
                HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        ApiErrorResponse error = ApiErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
