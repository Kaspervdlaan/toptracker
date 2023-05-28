package com.example.TopTracker.repository;

import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Boulder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoulderRepository extends JpaRepository<Boulder, Long> {
    List<Boulder> findAll();
}
