package com.example.TopTracker.controllers;


import com.example.TopTracker.dto.FileUploadResponse;
import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.service.UploadService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;

@CrossOrigin
@RestController
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public FileUploadResponse singleFileUpload(@RequestParam("file")MultipartFile file) throws IOException {

        FileDocument fileDocument = uploadService.uploadFile(file);
        String url = String.valueOf(ServletUriComponentsBuilder.fromCurrentContextPath().path("/download").path(Objects.requireNonNull(file.getOriginalFilename())));

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType);
    }
}
