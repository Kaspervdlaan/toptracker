package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.service.AreaService;
import com.example.TopTracker.service.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("blocks")
public class BlockController {
    private final BlockService blockService;
    private final AreaService areaService;

    public BlockController(BlockService blockService, AreaService areaService) {
        this.blockService = blockService;
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<BlockDto> createBlock(@RequestBody BlockDto blockDto) {
        BlockDto b = blockService.createBlock(blockDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(b.getId()).toUri();

        return ResponseEntity.created(uri).body(b);
    }

    @GetMapping
    public ResponseEntity<List<BlockDto>> getBlocks() {
        List<BlockDto> blockDtos = blockService.getAllBlocks();

        return ResponseEntity.ok().body(blockDtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable Long id) {
        BlockDto blockDto = blockService.getBlockById(id);

        return ResponseEntity.ok(blockDto);
    }

//    @PostMapping("/{block_id}/areas/{area_id}")
//    public ResponseEntity<Object> addBlockToArea(@PathVariable("area_id") Long area_id, @PathVariable("block_id") Long block_id) {
//        blockService.addBlockToArea(area_id, block_id);
//
//        return ResponseEntity.noContent().build();
//    }


    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateBlockById(@PathVariable Long id, @RequestBody BlockDto blockDto) {
        BlockDto blockDTO = blockService.updateBlock(id, blockDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(blockDTO).toUri();

        return ResponseEntity.created(uri).body(blockDTO);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Object> deleteBlockById(@PathVariable Long id) {
        blockService.deleteBlockById(id);
        return ResponseEntity.noContent().build();
    }
}
