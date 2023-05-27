package com.example.TopTracker.repository;

import com.example.TopTracker.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRolename(String rolename);
}