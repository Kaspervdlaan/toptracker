package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    private final AreaRepository areaRepo;
    private final BlockRepository blockRepo;

    public AreaService(AreaRepository areaRepo, BlockRepository blockRepo) {
        this.areaRepo = areaRepo;
        this.blockRepo = blockRepo;
    }

    public Long createArea(AreaDto areaDto) {
        Area a = new Area();
        a.setName(areaDto.name);
        a.setAddress(areaDto.address);
        a.setDescription(areaDto.description);

        Block b = blockRepo.findById(areaDto.blockId).get();
        a.setBlocks(b.getArea().getBlocks());

        areaRepo.save(a);

        return a.getId();
    }
}
