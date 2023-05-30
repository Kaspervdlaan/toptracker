package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class  AreaServiceTest {

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

        when(areaRepository.findById(anyLong())).thenReturn(Optional.of(a));

        AreaDto areaDto = areaService.getAreaById(a.getId());

        assertEquals("Avalonia", areaDto.getName());
        assertEquals("Duitsland", areaDto.getAddress());
        assertEquals("Dug out by Daniel", areaDto.getDescription());
        assertEquals(blocks, a.getBlocks());
    }


    @Test
    void shouldReturnListOfAreas() {
        // Arrange
        List<Area> areas = new ArrayList<>();
        Area area1 = new Area();
        area1.setId(1L);
        area1.setName("Area 1");
        area1.setAddress("Address 1");
        area1.setDescription("Description 1");
        areas.add(area1);

        Area area2 = new Area();
        area2.setId(2L);
        area2.setName("Area 2");
        area2.setAddress("Address 2");
        area2.setDescription("Description 2");
        areas.add(area2);

        when(areaRepository.findAll()).thenReturn(areas);

        // Act
        List<AreaDto> result = areaService.getAllAreas();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        AreaDto resultArea1 = result.get(0);
        assertEquals(1L, resultArea1.getId());
        assertEquals("Area 1", resultArea1.getName());
        assertEquals("Address 1", resultArea1.getAddress());
        assertEquals("Description 1", resultArea1.getDescription());

        AreaDto resultArea2 = result.get(1);
        assertEquals(2L, resultArea2.getId());
        assertEquals("Area 2", resultArea2.getName());
        assertEquals("Address 2", resultArea2.getAddress());
        assertEquals("Description 2", resultArea2.getDescription());
    }

    @Test
    void getAreaById_ExistingId_ReturnsAreaDto() {
        // Arrange
        Area area = new Area();
        area.setId(1L);
        area.setName("Area 1");
        area.setAddress("Address 1");
        area.setDescription("Description 1");

        when(areaRepository.findById(1L)).thenReturn(Optional.of(area));

        // Act
        AreaDto result = areaService.getAreaById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Area 1", result.getName());
        assertEquals("Address 1", result.getAddress());
        assertEquals("Description 1", result.getDescription());
    }

    @Test
    void shouldReturnThrownException() {
        // Arrange
        when(areaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> areaService.getAreaById(1L));
    }

    @Test
    void shouldReturnUpdatedArea() {
        // Arrange
        Area existingArea = new Area();
        existingArea.setId(1L);
        existingArea.setName("Area 1");
        existingArea.setAddress("Address 1");
        existingArea.setDescription("Description 1");

        when(areaRepository.findById(1L)).thenReturn(Optional.of(existingArea));

        AreaDto updatedAreaDto = new AreaDto();
        updatedAreaDto.setName("Updated Area");
        updatedAreaDto.setAddress("Updated Address");
        updatedAreaDto.setDescription("Updated Description");

        // Act
        AreaDto result = areaService.updateArea(1L, updatedAreaDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated Area", result.getName());
        assertEquals("Updated Address", result.getAddress());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    void shouldReturnThrownExceptionId() {
        // Arrange
        when(areaRepository.findById(1L)).thenReturn(Optional.empty());

        AreaDto updatedAreaDto = new AreaDto();
        updatedAreaDto.setName("Updated Area");
        updatedAreaDto.setAddress("Updated Address");
        updatedAreaDto.setDescription("Updated Description");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> areaService.updateArea(1L, updatedAreaDto));
    }

    @Test
    void deleteAreaById_ExistingId_DeletesArea() {
        // Arrange
        doNothing().when(areaRepository).deleteById(1L);

        // Act
        assertDoesNotThrow(() -> areaService.deleteAreaById(1L));

        // Assert
        verify(areaRepository, times(1)).deleteById(1L);
    }

}