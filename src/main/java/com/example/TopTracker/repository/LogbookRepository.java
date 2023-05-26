package com.example.TopTracker.repository;

import com.example.TopTracker.models.Logbook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogbookRepository extends JpaRepository<Logbook, Long> {
}
