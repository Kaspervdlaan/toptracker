package com.example.TopTracker.service;

import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.repository.BlockRepository;
import com.example.TopTracker.repository.BoulderRepository;
import org.springframework.stereotype.Service;

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
            boulderDTO.setBlock_id(boulder.getId());
        }

        boulderDTO.setId(boulder.getId());

        return boulderDTO;
    }
}
