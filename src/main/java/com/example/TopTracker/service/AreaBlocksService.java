package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaBlocksService {

    private final AreaRepository areaRepository;
    private final BlockRepository blockRepository;


    public AreaBlocksService(AreaRepository areaRepository, BlockRepository blockRepository) {
        this.areaRepository = areaRepository;
        this.blockRepository = blockRepository;
    }

    public List<BlockDto> getAreaBlocks(Long areaId) {

        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Not found"));

            List<Block> blocks = area.getBlocks();

            List<BlockDto> blockDtos = new ArrayList<>();
            for (Block block : blocks) {
                BlockDto blockDto = new BlockDto();
                blockDto.setId(block.getId());
                blockDto.setBlockName(block.getBlockName());
                blockDto.setStoneType(block.getStoneType());
                blockDto.setArea_id(block.getArea().getId());
                blockDtos.add(blockDto);
            }
            return blockDtos;
        }

    public AreaDto addBlockToArea(Long areaId, Long blockId) {
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Not found"));
        Block block = blockRepository.findById(blockId).orElseThrow(() -> new RuntimeException("Not found"));

        List<Block> blocks = area.getBlocks();
        blocks.add(block);
        area.setBlocks(blocks);

        Area a = areaRepository.save(area);

        return areaBlockToDto(a);
    }

    public AreaDto areaBlockToDto(Area area) {
        AreaDto areaDto = new AreaDto();
        areaDto.setBlocks(area.getBlocks());
        return areaDto;
    }
}
