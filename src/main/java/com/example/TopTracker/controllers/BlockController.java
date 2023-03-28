package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.service.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
