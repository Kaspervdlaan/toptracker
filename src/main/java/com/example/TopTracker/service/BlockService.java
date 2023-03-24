package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class BlockService {
    private final BlockRepository blockRepo;

    public BlockService(BlockRepository blockRepo) {
        this.blockRepo = blockRepo;
    }

    public Block createBlock(BlockDto blockDto) {
        Block b = new Block();
        b.setBlockName(blockDto.blockName);
        b.setStoneType(blockDto.stoneType);

        blockRepo.save(b);

        b.getId();

        blockRepo.save(b);

        return b;
    }
}
