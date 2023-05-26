package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AttemptDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Attempt;
import com.example.TopTracker.service.AttemptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("attempts")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping
    public ResponseEntity<AttemptDto> addAttempt(@RequestBody AttemptDto attemptDto) {
        AttemptDto a = attemptService.addAttempt(attemptDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(attemptDto).toUri();

        return ResponseEntity.created(uri).body(a);
    }

    @GetMapping
    public ResponseEntity<List<AttemptDto>> getAttempt() {
        List<AttemptDto> attemptDtos = attemptService.getAllAttempts();

        return ResponseEntity.ok().body(attemptDtos);
    }

    @PutMapping("/{attempt_id}")
    public ResponseEntity<Object> updateAttemptById(@PathVariable("attempt_id") Long id, @RequestBody AttemptDto attemptDto) {
        AttemptDto attemptDTO = attemptService.updateAttempt(id, attemptDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(attemptDto).toUri();

        return ResponseEntity.created(uri).body(attemptDTO);
    }

    @DeleteMapping("/{attempt_id}")
    public ResponseEntity<Object> deleteAttemptById(@PathVariable("attempt_id") Long id) {
        attemptService.deleteAttemptById(id);
        return ResponseEntity.noContent().build();
    }
}
