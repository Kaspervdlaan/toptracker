package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AttemptDto;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.service.AttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("attempts")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public ResponseEntity<Attempt> addAttempt(@RequestBody AttemptDto attemptDto) {
        Attempt a = attemptService.addAttempt(attemptDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(attemptDto).toUri();

        return ResponseEntity.created(uri).body(a);
    }
}
