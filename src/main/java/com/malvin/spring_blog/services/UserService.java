package com.malvin.spring_blog.services;

import java.util.UUID;

import com.malvin.spring_blog.domain.entities.User;

public interface UserService {
    User getUserById(UUID id);
}
