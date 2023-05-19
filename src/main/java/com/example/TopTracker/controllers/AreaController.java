package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.service.AreaBlocksService;
import com.example.TopTracker.service.AreaService;
import com.example.TopTracker.service.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("areas")
public class AreaController {

    private final AreaService areaService;
    private final BlockService blockService;

    private final AreaBlocksService areaBlocksService;


    public AreaController(AreaService areaService, BlockService blockService, AreaBlocksService areaBlocksService) {
        this.areaService = areaService;
        this.blockService = blockService;
        this.areaBlocksService = areaBlocksService;
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

    @PutMapping("/{area_id}/blocks/{block_id}")
    public ResponseEntity<Object> addBlockToArea(@PathVariable Long area_id, @PathVariable Long block_id) {
        AreaDto areaDto = areaBlocksService.addBlockToArea(area_id, block_id);

        return ResponseEntity.ok(areaDto);
    }

    @GetMapping("/{area_id}/blocks")
    public ResponseEntity<List<BlockDto>> getAreaBlocks(@PathVariable Long area_id) {
        List<BlockDto> blockDtos = areaBlocksService.getAreaBlocks(area_id);
        return ResponseEntity.ok(blockDtos);
    }

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
