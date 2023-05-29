package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.repository.AreaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class AreaServiceTest {

    @Mock
    AreaRepository areaRepository;
    @InjectMocks
    AreaService areaService;

    @Test
    void shouldReturnArea() {
        Area a = new Area();
        a.setId(1L);
        a.setName("Avalonia");
        a.setAddress("Duitsland");
        a.setDescription("Dug out by Daniel");
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block());
        a.setBlocks(blocks);

        Mockito.when(areaRepository.findById(anyLong())).thenReturn(Optional.of(a));

        AreaDto areaDto = areaService.getAreaById(a.getId());

        assertEquals("Avalonia", areaDto.getName());
        assertEquals("Duitsland", areaDto.getAddress());
        assertEquals("Dug out by Daniel", areaDto.getDescription());
        assertEquals(blocks, a.getBlocks());
    }

}