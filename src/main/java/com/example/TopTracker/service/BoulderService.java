package com.example.TopTracker.service;

import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.repository.BoulderRepository;
import org.springframework.stereotype.Service;

@Service
public class BoulderService {
    private final BoulderRepository boulderRepo;

    public BoulderService(BoulderRepository boulderRepo) {
        this.boulderRepo = boulderRepo;
    }

    public Boulder createBoulder(BoulderDto boulderDto) {
        Boulder b = new Boulder();
        b.setBoulderName(boulderDto.boulderName);
        b.setBoulderGrade(boulderDto.boulderGrade);
        b.setHoldType(boulderDto.holdType);
        b.setBoulderNotes(boulderDto.boulderNotes);
        b.setBoulderImage(boulderDto.boulderImage);

        boulderRepo.save(b);

        b.getId();

        boulderRepo.save(b);

        return b;
    }
}
