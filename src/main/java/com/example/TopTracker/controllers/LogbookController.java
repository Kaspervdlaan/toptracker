package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.LogbookDto;
import com.example.TopTracker.service.LogbookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("logbooks")
public class LogbookController {
    private final LogbookService logbookService;

    public LogbookController(LogbookService logbookService) {
        this.logbookService = logbookService;
    }

    @PostMapping
    public ResponseEntity<LogbookDto> createLogbook(@RequestBody LogbookDto logbookDto) {
        LogbookDto l = logbookService.createLogbook(logbookDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(l.getId()).toUri();

        return ResponseEntity.created(uri).body(l);
    }

    @GetMapping
    public ResponseEntity<List<LogbookDto>> getLogbooks() {
        List<LogbookDto> logbookDtos = logbookService.getAllLogbooks();
        return ResponseEntity.ok().body(logbookDtos);
    }
}
