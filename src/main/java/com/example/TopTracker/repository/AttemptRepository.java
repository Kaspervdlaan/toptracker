package com.example.TopTracker.repository;

import com.example.TopTracker.models.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
}
