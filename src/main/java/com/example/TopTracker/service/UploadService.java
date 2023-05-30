package com.example.TopTracker.service;

import com.example.TopTracker.JwtUtils.JwtUtils;
import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.DocFileRepository;
import com.example.TopTracker.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class UploadService {
    private final DocFileRepository docFileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    public UploadService(DocFileRepository docFileRepository) {
        this.docFileRepository = docFileRepository;
    }

    public FileDocument uploadFile(String fileName, MultipartFile file, @RequestHeader("Authorization") String authHeader) throws IOException {

        User u = userRepository.findByUsername(jwtUtils.extractUsernameFromToken(authHeader));

        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(fileName);
        fileDocument.setDocFile(file.getBytes());
        fileDocument.setUser(u);

        return docFileRepository.save(fileDocument);
    }

    public ResponseEntity<byte[]> downloadFile(Long file_id, HttpServletRequest request, @RequestHeader("Authorization") String authHeader ) {
        User u = userRepository.findByUsername(jwtUtils.extractUsernameFromToken(authHeader));

        List<FileDocument> fileDocuments = u.getFileDocuments();

        int file = (int) (file_id -1);

        FileDocument f = fileDocuments.get(file);

        String mimeType = request.getServletContext().getMimeType(f.getFileName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + f.getFileName()).body(f.getDocFile());
    }
}
