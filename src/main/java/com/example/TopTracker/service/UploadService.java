package com.example.TopTracker.service;

import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.repository.DocFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class UploadService {
    private final DocFileRepository docFileRepository;

    public UploadService(DocFileRepository docFileRepository) {
        this.docFileRepository = docFileRepository;
    }

    public FileDocument uploadFile(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(name);
        fileDocument.setDocFile(file.getBytes());

        docFileRepository.save(fileDocument);

        return fileDocument;
    }
}
