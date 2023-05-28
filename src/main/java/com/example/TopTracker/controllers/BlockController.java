package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.service.AreaService;
import com.example.TopTracker.service.BlockBouldersService;
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
    private final BlockBouldersService blockBouldersService;


    public BlockController(BlockService blockService, BlockBouldersService blockBouldersService, AreaService areaService) {
        this.blockService = blockService;
        this.blockBouldersService = blockBouldersService;
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

    @GetMapping("/{block_id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable("block_id") Long id) {
        BlockDto blockDto = blockService.getBlockById(id);

        return ResponseEntity.ok(blockDto);
    }

    @GetMapping("/{block_id}/boulders")
    public ResponseEntity<List<BoulderDto>> getAreaBlocks(@PathVariable Long block_id) {
        List<BoulderDto> boulderDtos = blockBouldersService.getBlockBoulders(block_id);
        return ResponseEntity.ok(boulderDtos);
    }

    @PutMapping("/{block_id}/boulders/{boulder_id}")
    public ResponseEntity<Object> addBoulderToBlock(@PathVariable("block_id") Long block_id, @PathVariable("boulder_id") Long boulder_id) {
        BlockDto blockDto = blockBouldersService.addBoulderToBlock(block_id, boulder_id);

        return ResponseEntity.ok(blockDto);
    }

    @PutMapping("/{blockId}")
    public ResponseEntity<Object> updateBlockById(@PathVariable("blockId") Long id, @RequestBody BlockDto blockDto) {
        BlockDto blockDTO = blockService.updateBlock(id, blockDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(blockDTO).toUri();

        return ResponseEntity.created(uri).body(blockDTO);
    }

    @DeleteMapping("/{blockId}")
    public ResponseEntity<Object> deleteBlockById(@PathVariable("blockId") Long id) {
        blockService.deleteBlockById(id);
        return ResponseEntity.noContent().build();
    }
}
