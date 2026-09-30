package com.malvin.spring_blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malvin.spring_blog.domain.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, UUID> {
    
}
