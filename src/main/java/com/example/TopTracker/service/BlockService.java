package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    private final BlockRepository blockRepository;

    private final AreaRepository areaRepository;

    public BlockService(BlockRepository blockRepo, AreaRepository areaRepository) {
        this.blockRepository = blockRepo;
        this.areaRepository = areaRepository;
    }

    public Block createBlock(BlockDto blockDto) {
        Block b = new Block();

        b.setBlockName(blockDto.blockName);
        b.setStoneType(blockDto.stoneType);

        blockRepository.save(b);

        return b;
    }



    public List<BlockDto> getAllBlocks() {
        List<BlockDto> blocks = new ArrayList<>();
        List<Block> blockList = blockRepository.findAll();

        for (Block b : blockList) {
            BlockDto blockDto = new BlockDto();
            blockDto.id = b.getId();
            blockDto.blockName = b.getBlockName();
            blockDto.stoneType = b.getStoneType();

            blocks.add(blockDto);
        }

        return blocks;
    }

    public BlockDto getBlockById(Long id) {
        Block b = blockRepository.findById(id).orElseThrow(() -> new RuntimeException("Block not found"));
        BlockDto blockDto = new BlockDto();

        blockDto.id = b.getId();
        blockDto.blockName = b.getBlockName();
        blockDto.stoneType = b.getStoneType();

        return blockDto;
    }

    public void addBlockToArea(Long blockId, Long areaId) {
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        Optional<Block> optionalBlock = blockRepository.findById(blockId);

        if (!optionalBlock.isEmpty() && !optionalArea.isEmpty()) {
            Area a = optionalArea.get();
            Block b = optionalBlock.get();

            b.setArea(a);
            blockRepository.save(b);
        }
    }

    public BlockDto updateBlock(Long id , BlockDto blockDto){
        Block b = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Block not found"));

        b.setBlockName(blockDto.blockName);
        b.setStoneType(blockDto.stoneType);

        blockRepository.save(b);

        return blockDto;
    }

    public void deleteBlockById(Long id) {
        Block b = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Block not found"));
        blockRepository.delete(b);
    }
}
