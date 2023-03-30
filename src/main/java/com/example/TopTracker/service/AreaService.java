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
public class AreaService {
    private final AreaRepository areaRepository;
    private final BlockRepository blockRepository;

    public AreaService(AreaRepository areaRepo, BlockRepository blockRepository) {
        this.areaRepository = areaRepo;
        this.blockRepository = blockRepository;
    }

    public AreaDto createArea(AreaDto areaDto) {
        Area a = new Area();
        AreaDto areaDTO = new AreaDto();

        a.setName(areaDto.name);
        a.setAddress(areaDto.address);
        a.setDescription(areaDto.description);


        Area area = areaRepository.save(a);
        areaDTO.setName(area.getName());
        areaDTO.setAddress(area.getAddress());
        areaDTO.setDescription(area.getDescription());


        areaDTO.setId(area.getId());

        return areaDTO;
    }

    public List<AreaDto> getAllAreas() {
        List<AreaDto> areas = new ArrayList<>();
        List<Area> areaList = areaRepository.findAll();

        for (Area a : areaList) {
            AreaDto areaDto = new AreaDto();
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

//    public void addBlockToArea(Long areaId, Long blockId) {
//        Optional<Area> optionalArea = areaRepository.findById(areaId);
//        Optional<Block> optionalBlock = blockRepository.findById(blockId);
//
//        if (!optionalBlock.isEmpty() && !optionalArea.isEmpty()) {
//            Area a = optionalArea.get();
//            Block b = optionalBlock.get();
//
//            b.setArea(a);
//            blockRepository.save(b);
//        }
//    }

    public AreaDto updateArea(Long id, AreaDto areaDto) {
        Area a = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        a.setName(areaDto.getName());
        a.setAddress(areaDto.getAddress());
        a.setDescription(areaDto.getDescription());

        areaRepository.save(a);

        return areaDto;
    }

    public void deleteAreaById(Long id) {
        Area a = areaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        areaRepository.delete(a);
    }
}
