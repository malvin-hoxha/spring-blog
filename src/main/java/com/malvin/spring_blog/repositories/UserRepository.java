package com.malvin.spring_blog.repositories;

import com.malvin.spring_blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    
}
