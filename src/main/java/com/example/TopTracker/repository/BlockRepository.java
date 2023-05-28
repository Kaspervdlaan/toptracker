package com.example.TopTracker.repository;

import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findAll();
}
