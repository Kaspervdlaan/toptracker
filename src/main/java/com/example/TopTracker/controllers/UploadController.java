package com.example.TopTracker.controllers;


import com.example.TopTracker.dto.FileDto;
import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@CrossOrigin
@RestController
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public FileDto singleFileUpload(@RequestParam( value = "file") MultipartFile file ) throws IOException {
        String fileName = System.currentTimeMillis() + "-" + new Random().nextInt(1000) + "-" + file.getOriginalFilename();
        FileDocument fileDocument = uploadService.uploadFile(fileName, file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();
        String contentType = file.getContentType();
        return new FileDto(fileDocument.getFileName(), contentType, url);
    }

    @GetMapping("/download/{fileName}")
    ResponseEntity<byte[]> singleFileDownload(@PathVariable String fileName, HttpServletRequest request) {
        return uploadService.downloadFile(fileName, request);
    }
}
