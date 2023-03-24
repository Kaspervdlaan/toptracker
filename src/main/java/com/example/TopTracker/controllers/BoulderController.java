package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.service.BoulderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("boulders")
public class BoulderController {
    private final BoulderService boulderService;

    public BoulderController(BoulderService boulderService) {
        this.boulderService = boulderService;
    }

    @PostMapping
    public ResponseEntity<Boulder> createBoulder(@RequestBody BoulderDto boulderDto) {
        Boulder b = boulderService.createBoulder(boulderDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(b.getBoulderName()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
