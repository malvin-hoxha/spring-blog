package com.malvin.spring_blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malvin.spring_blog.domain.entities.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {
    
}
