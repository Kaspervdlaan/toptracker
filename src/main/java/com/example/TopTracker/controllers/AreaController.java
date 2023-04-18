package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.service.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("areas")
public class AreaController {

    private final AreaService areaService;


    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<AreaDto> createArea(@RequestBody AreaDto areaDto) {
        AreaDto a = areaService.createArea(areaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(a.getBlocks()).toUri();

        return ResponseEntity.created(uri).body(a);
    }

    @GetMapping
    public ResponseEntity<List<AreaDto>> getAreas() {
        List<AreaDto> areaDtos = areaService.getAllAreas();

        return ResponseEntity.ok().body(areaDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AreaDto> getUserById(@PathVariable Long id) {
        AreaDto areaDto = areaService.getAreaById(id);

        return ResponseEntity.ok(areaDto);
    }

//    @PostMapping("/{area_id}/blocks/{block_id}")
//    public ResponseEntity<Object> addBlockToArea(@PathVariable("area_id") Long area_id, @PathVariable("block_id") Long block_id) {
//        areaService.addBlockToArea(area_id, block_id);
//
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateArea(@PathVariable Long id, @RequestBody AreaDto areaDto) {
        AreaDto areaDTO = areaService.updateArea(id, areaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(areaDTO).toUri();

        return ResponseEntity.created(uri).body(areaDTO);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Object> deleteAreaById(@PathVariable Long id) {
        areaService.deleteAreaById(id);
        return ResponseEntity.noContent().build();
    }
}
