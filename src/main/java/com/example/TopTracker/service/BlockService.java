package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockService {
    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepo) {
        this.blockRepository = blockRepo;
    }

    public Block createBlock(BlockDto blockDto) {
        Block b = new Block();

        b.setBlockName(blockDto.blockName);
        b.setStoneType(blockDto.stoneType);

        blockRepository.save(b);
        blockDto.id = b.getId();
        blockRepository.save(b);

        return b;
    }

    public List<BlockDto> getAllBlocks() {
        List<BlockDto> blocks = new ArrayList<>();
        List<Block> blockList = blockRepository.findAll();
        BlockDto blockDto = new BlockDto();
        for (Block b : blockList) {

            blockDto.id = b.getId();
            blockDto.blockName = b.getBlockName();
            blockDto.stoneType = b.getStoneType();

            blocks.add(blockDto);
        }

        return blocks;
    }

    public BlockDto getUserById(Long id) {
        Block b = blockRepository.findById(id).orElseThrow(() -> new RuntimeException("Block not found"));
        BlockDto blockDto = new BlockDto();

        blockDto.id = b.getId();
        blockDto.blockName = b.getBlockName();
        blockDto.stoneType = b.getStoneType();

        return blockDto;
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
