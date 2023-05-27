package com.example.TopTracker.repository;

import com.example.TopTracker.models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
}
