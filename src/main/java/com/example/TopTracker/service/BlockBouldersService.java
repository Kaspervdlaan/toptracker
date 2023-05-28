package com.example.TopTracker.service;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.repository.BlockRepository;
import com.example.TopTracker.repository.BoulderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockBouldersService {
    private final BlockRepository blockRepository;
    private final BoulderRepository boulderRepository;

    public BlockBouldersService(BlockRepository blockRepository, BoulderRepository boulderRepository) {
        this.blockRepository = blockRepository;
        this.boulderRepository = boulderRepository;
    }

    public List<BoulderDto> getBlockBoulders(Long blockId) {
        Block block = blockRepository.findById(blockId).orElseThrow(() -> new RuntimeException("Not found"));

        List<Boulder> boulders = block.getBoulders();

        List<BoulderDto> boulderDtos = new ArrayList<>();
        for (Boulder boulder : boulders) {
            BoulderDto boulderDto = new BoulderDto();
            boulderDto.setId(boulder.getId());
            boulderDto.setBoulderName(boulder.getBoulderName());
            boulderDto.setBoulderImage(boulder.getBoulderImage());
            boulderDto.setBoulderNotes(boulder.getBoulderNotes());
            boulderDto.setBoulderGrade(boulder.getBoulderGrade());
            boulderDto.setHoldType(boulder.getHoldType());
            boulderDto.setBlock_id(boulder.getBlock().getId());
            boulderDtos.add(boulderDto);
        }
        return boulderDtos;
    }

    public BlockDto addBoulderToBlock(Long blockId, Long boulderId) {
        Block block = blockRepository.findById(blockId).orElseThrow(() -> new RuntimeException("Not found"));
        Boulder boulder = boulderRepository.findById(boulderId).orElseThrow(() -> new RuntimeException("Not found"));

        List<Boulder> boulders = block.getBoulders();
        boulders.add(boulder);
        block.setBoulders(boulders);

        Block b = blockRepository.save(block);

        return blockBoulderToDto(b);
    }

    public BlockDto blockBoulderToDto(Block block) {
        BlockDto blockDto = new BlockDto();
        blockDto.setBoulders(block.getBoulders());
        return blockDto;
    }

}
