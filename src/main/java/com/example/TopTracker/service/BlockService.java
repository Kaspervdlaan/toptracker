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

    public BlockService(BlockRepository blockRepository, AreaRepository areaRepository) {
        this.blockRepository = blockRepository;
        this.areaRepository = areaRepository;
    }

    public BlockDto createBlock(BlockDto blockDto) {
        Block b = new Block();
        BlockDto blockDTO = new BlockDto();

        b.setBlockName(blockDto.blockName);
        b.setStoneType(blockDto.stoneType);

        Block block =  blockRepository.save(b);
        blockDTO.setBlockName(block.getBlockName());
        blockDTO.setStoneType(block.getStoneType());
        blockDTO.setId(block.getId());

        return blockDTO;
    }

    public List<BlockDto> getAllBlocks() {
        List<BlockDto> blocks = new ArrayList<>();
        List<Block> blockList = blockRepository.findAll();

        for (Block b : blockList) {
            BlockDto blockDto = new BlockDto();
            blockDto.id = b.getId();
            blockDto.blockName = b.getBlockName();
            blockDto.stoneType = b.getStoneType();
            if (b.getArea() != null) {
                blockDto.setArea_id(b.getArea().getId());
            }
            if (b.getBoulders() != null) {
                blockDto.setBoulders(b.getBoulders());
            }

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

        if (b.getArea() != null) {
            blockDto.setArea_id(b.getArea().getId());
        }
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
