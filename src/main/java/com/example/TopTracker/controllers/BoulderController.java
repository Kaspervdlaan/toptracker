package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.service.BoulderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("boulders")
public class BoulderController {
    private final BoulderService boulderService;

    public BoulderController(BoulderService boulderService) {
        this.boulderService = boulderService;
    }

    @PostMapping
    public ResponseEntity<BoulderDto> createBoulder(@RequestBody BoulderDto boulderDto) {
        BoulderDto b = boulderService.createBoulder(boulderDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(b.getId()).toUri();

        return ResponseEntity.created(uri).body(b);
    }

    @GetMapping
    public ResponseEntity<List<BoulderDto>> getBoulders() {
        List<BoulderDto> boulderDtos = boulderService.getAllBoulders();

        return ResponseEntity.ok().body(boulderDtos);
    }

    @GetMapping("/{boulderId}")
    public ResponseEntity<BoulderDto> getBoulderById(@PathVariable("boulderId") Long id) {
        BoulderDto boulderDto = boulderService.getBoulderById(id);

        return ResponseEntity.ok().body(boulderDto);
    }

    @PutMapping("/{boulderId}")
    public ResponseEntity<Object> updateBoulderById(@PathVariable("boulderId") Long id, @RequestBody BoulderDto boulderDto) {
        BoulderDto boulderDTO = boulderService.updateBoulder(id, boulderDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(boulderDTO).toUri();

        return ResponseEntity.created(uri).body(boulderDTO);
    }

    @DeleteMapping("/{boulderId}")
    public ResponseEntity<Object> deleteBoulderById(@PathVariable("boulderId") Long id) {
        boulderService.deleteBoulderById(id);
        return ResponseEntity.noContent().build();
    }
}
