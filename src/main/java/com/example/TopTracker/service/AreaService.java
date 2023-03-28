package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
    private final AreaRepository areaRepository;
    private final BlockRepository blockRepository;

    public AreaService(AreaRepository areaRepo, BlockRepository blockRepo) {
        this.areaRepository = areaRepo;
        this.blockRepository = blockRepo;
    }

    public Area createArea(AreaDto areaDto) {
        Area a = new Area();
        a.setName(areaDto.name);
        a.setAddress(areaDto.address);
        a.setDescription(areaDto.description);

//        Block b = blockRepository.findById(areaDto.blockId).get();
//        a.setBlocks(b.getArea().getBlocks());

        areaRepository.save(a);

        a.getId();
        areaRepository.save(a);

        return a;
    }
}
