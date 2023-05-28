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

@CrossOrigin
@RestController
public class UploadController {
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileDto> singleFileUpload(@RequestParam("file")MultipartFile file) throws IOException {

        FileDocument fileDocument = uploadService.uploadFile(file);
        FileDto fileDto = uploadService.convertToDto(fileDocument);
        return new ResponseEntity<>(fileDto, HttpStatus.CREATED);


//        FileDto fileDto = uploadService.uploadFile(file);
//
//        String contentType = file.getContentType();
//
//        return new FileDto(fileDto.getFileName(), contentType, url);
    }

//    @GetMapping("/download/{filename}")
//    ResponseEntity<byte[]> singleFileDownload(@PathVariable String fileName, HttpServletRequest request) {
//        return uploadService.downloadFile(fileName, request);
//    }
}
