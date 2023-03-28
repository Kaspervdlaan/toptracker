package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.UserDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.User;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService {
    private final AreaRepository areaRepository;
//    private final BlockRepository blockRepository;

    public AreaService(AreaRepository areaRepo) { //, BlockRepository blockRepo
        this.areaRepository = areaRepo;
//        this.blockRepository = blockRepo;
    }

    public Area createArea(AreaDto areaDto) {
        Area a = new Area();
        a.setName(areaDto.name);
        a.setAddress(areaDto.address);
        a.setDescription(areaDto.description);

//        Block b = blockRepository.findById(areaDto.blockId).get();
//        a.setBlocks(b.getArea().getBlocks());

        areaRepository.save(a);

        areaDto.id = a.getId();
        areaRepository.save(a);

        return a;
    }

    public List<AreaDto> getAllAreas() {
        List<AreaDto> areas = new ArrayList<>();
        List<Area> areaList = areaRepository.findAll();
        AreaDto areaDto = new AreaDto();
        for (Area a : areaList) {

            areaDto.id = a.getId();
            areaDto.name = a.getName();
            areaDto.address = a.getAddress();
            areaDto.description = a.getDescription();
            areas.add(areaDto);
        }

        return areas;
    }

    public AreaDto getAreaById(Long id) {
        Area a = areaRepository.findById(id).orElseThrow(() -> new RuntimeException("Area not found"));
        AreaDto areaDto = new AreaDto();

        areaDto.id = a.getId();
        areaDto.name = a.getName();
        areaDto.address = a.getAddress();
        areaDto.description = a.getDescription();

        return areaDto;
    }

    public AreaDto updateArea(Long id, AreaDto areaDto) {
        Area a = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        a.setName(areaDto.getName());
        a.setAddress(areaDto.getAddress());
        a.setDescription(areaDto.getDescription());

        areaRepository.save(a);
        areaDto.id = a.getId();
        areaRepository.save(a);
        return areaDto;
    }

    public void deleteAreaById(Long id) {
        Area a = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        areaRepository.delete(a);
    }
}
