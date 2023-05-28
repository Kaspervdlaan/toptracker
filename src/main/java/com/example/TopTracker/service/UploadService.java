package com.example.TopTracker.service;

import com.example.TopTracker.dto.FileDto;
import com.example.TopTracker.models.FileDocument;
import com.example.TopTracker.repository.DocFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UploadService {
    private final DocFileRepository docFileRepository;

    public UploadService(DocFileRepository docFileRepository) {
        this.docFileRepository = docFileRepository;
    }

    public FileDocument uploadFile(MultipartFile file) throws IOException {
        FileDocument fileDocument = new FileDocument();
        fileDocument.setFileName(file.getOriginalFilename());
        fileDocument.setDocFile(file.getBytes());

        return docFileRepository.save(fileDocument);
    }

    public FileDto convertToDto(FileDocument fileDocument) {
        FileDto fileDto = new FileDto();
        fileDto.setFileName(fileDocument.getFileName());
        fileDto.setUrl(Base64.getEncoder().encodeToString(fileDocument.getDocFile()));
        return fileDto;
    }



//    public ResponseEntity<byte[]> downloadFile(String fileName, HttpServletRequest request) {
//        FileDocument document = docFileRepository.findByFileName(fileName);
//        String mimeType = request.getServletContext().getMimeType(document.getFileName());
//
//        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
//    }
}
