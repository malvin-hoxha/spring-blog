package com.malvin.spring_blog.repositories;

import com.malvin.spring_blog.domain.entities.Category;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID>{
    
}
