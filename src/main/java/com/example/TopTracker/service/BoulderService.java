package com.example.TopTracker.service;

import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.repository.BlockRepository;
import com.example.TopTracker.repository.BoulderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoulderService {
    private final BoulderRepository boulderRepository;
    private final BlockRepository blockRepository;

    public BoulderService(BoulderRepository boulderRepository, BlockRepository blockRepository) {
        this.boulderRepository = boulderRepository;
        this.blockRepository = blockRepository;
    }

    public BoulderDto createBoulder(BoulderDto boulderDto) {
        Boulder b = new Boulder();
        BoulderDto boulderDTO = new BoulderDto();

        b.setBoulderName(boulderDto.boulderName);
        b.setBoulderGrade(boulderDto.boulderGrade);
        b.setHoldType(boulderDto.holdType);
        b.setBoulderNotes(boulderDto.boulderNotes);
        b.setBoulderImage(boulderDto.boulderImage);

        if (boulderDto.block_id != null) {
            Optional<Block> blockOptional = blockRepository.findById(boulderDto.block_id);

            if (blockOptional.isPresent()) {
                b.setBlock(blockOptional.get());
            }
        }

        Boulder boulder = boulderRepository.save(b);
        boulderDTO.setBoulderName(boulder.getBoulderName());
        boulderDTO.setBoulderGrade(boulder.getBoulderGrade());
        boulderDTO.setBoulderNotes(boulder.getBoulderNotes());
        boulderDTO.setBoulderImage(boulder.getBoulderImage());
        boulderDTO.setHoldType(boulder.getHoldType());

        if (boulder.getBlock() != null) {
            boulderDTO.setBlock_id(boulder.getBlock().getId());
        }

        boulderDTO.setId(boulder.getId());

        return boulderDTO;
    }

    public List<BoulderDto> getAllBoulders() {
        List<BoulderDto> boulders = new ArrayList<>();
        List<Boulder> boulderList = boulderRepository.findAll();

        for (Boulder b : boulderList) {
            BoulderDto boulderDto = new BoulderDto();
            boulderDto.id = b.getId();
            boulderDto.boulderName = b.getBoulderName();
            boulderDto.boulderGrade = b.getBoulderGrade();
            boulderDto.boulderNotes = b.getBoulderNotes();
            boulderDto.boulderImage = b.getBoulderImage();
            boulderDto.holdType = b.getHoldType();

            if (b.getBlock() != null) {
                boulderDto.setBlock_id(b.getBlock().getId());
            }

            boulders.add(boulderDto);
        }

        return boulders;
    }

    public BoulderDto getBoulderById(Long id) {
        Boulder b = boulderRepository.findById(id).orElseThrow(() -> new RuntimeException("Boulder not found"));
        BoulderDto boulderDto = new BoulderDto();

        boulderDto.id = b.getId();
        boulderDto.boulderName = b.getBoulderName();
        boulderDto.boulderGrade = b.getBoulderGrade();
        boulderDto.boulderNotes = b.getBoulderNotes();
        boulderDto.boulderImage = b.getBoulderImage();
        boulderDto.holdType = b.getHoldType();

        if (b.getBlock() != null) {
            boulderDto.setBlock_id(b.getBlock().getId());
        }
        return boulderDto;
    }

    public BoulderDto updateBoulder(Long id, BoulderDto boulderDto) {
        Boulder b = boulderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boulder not found"));

        b.setBoulderName(boulderDto.boulderName);
        b.setBoulderGrade(boulderDto.boulderGrade);
        b.setBoulderNotes(boulderDto.boulderNotes);
        b.setHoldType(boulderDto.holdType);
        b.setBoulderImage(boulderDto.boulderImage);

        boulderRepository.save(b);

        return boulderDto;
    }

    public void deleteBoulderById(Long id) {
        Boulder b = boulderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Boulder not found"));
        boulderRepository.delete(b);
    }
}
