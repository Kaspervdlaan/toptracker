package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.repository.AreaRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    private final AreaRepository areaRepo;

    public AreaService(AreaRepository areaRepo) {
        this.areaRepo = areaRepo;
    }

    public Area createArea(AreaDto areaDto) {
        Area a = new Area();
        a.setName(areaDto.name);
        a.setAddress(areaDto.address);
        a.setDescription(areaDto.description);

        areaRepo.save(a);

        a.getId();

        areaRepo.save(a);

        return a;
    }
}
