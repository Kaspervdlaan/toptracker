package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.User;
import com.example.TopTracker.service.AreaService;
import com.example.TopTracker.service.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("areas")
public class AreaController {

    private final AreaService areaService;


    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<Area> createArea(@RequestBody AreaDto areaDto) {
        Area a = areaService.createArea(areaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(areaDto).toUri();

        return ResponseEntity.created(uri).body(a);

    }
}
