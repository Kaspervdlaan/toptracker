package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.models.Block;
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

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @PostMapping
    public ResponseEntity<Block> createBlock(@RequestBody BlockDto blockDto) {
        Block b = blockService.createBlock(blockDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(blockDto).toUri();

        return ResponseEntity.created(uri).body(b);
    }

    @GetMapping
    public ResponseEntity<List<BlockDto>> getBlocks() {
        List<BlockDto> blockDtos = blockService.getAllBlocks();

        return ResponseEntity.ok().body(blockDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockDto> getBlockById(@PathVariable Long id) {
        BlockDto blockDto = blockService.getUserById(id);

        return ResponseEntity.ok(blockDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBlockById(@PathVariable Long id, @RequestBody BlockDto blockDto) {
        BlockDto blockDTO = blockService.updateBlock(id, blockDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(blockDTO).toUri();

        return ResponseEntity.created(uri).body(blockDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBlockById(@PathVariable Long id) {
        blockService.deleteBlockById(id);
        return ResponseEntity.noContent().build();
    }
}
