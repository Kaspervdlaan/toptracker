package com.example.TopTracker.repository;

import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
