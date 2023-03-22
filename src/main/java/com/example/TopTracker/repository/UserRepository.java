package com.example.TopTracker.repository;

import com.example.TopTracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //findbyusername
}
