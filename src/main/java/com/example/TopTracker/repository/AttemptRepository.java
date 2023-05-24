package com.example.TopTracker.repository;

import com.example.TopTracker.models.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    List<Attempt> findAll();
}
