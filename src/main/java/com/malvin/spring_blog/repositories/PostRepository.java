package com.malvin.spring_blog.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malvin.spring_blog.domain.PostStatus;
import com.malvin.spring_blog.domain.entities.Category;
import com.malvin.spring_blog.domain.entities.Post;
import com.malvin.spring_blog.domain.entities.Tag;
import com.malvin.spring_blog.domain.entities.User;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post> findAllByStatusAndCategory(PostStatus status, Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus status, Tag tag);
    List<Post> findAllByStatus(PostStatus status);
    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);
}
