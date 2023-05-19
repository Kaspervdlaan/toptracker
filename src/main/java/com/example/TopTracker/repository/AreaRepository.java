package com.example.TopTracker.repository;

import com.example.TopTracker.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAll();

}
