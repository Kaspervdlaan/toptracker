package com.example.TopTracker.service;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.exeption.ResourceNotFoundException;
import com.example.TopTracker.models.Area;
import com.example.TopTracker.models.Block;
import com.example.TopTracker.models.Boulder;
import com.example.TopTracker.repository.AreaRepository;
import com.example.TopTracker.repository.BlockRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class  BlockServiceTest {

    @Mock
    BlockRepository blockRepository;
    @InjectMocks
    BlockService blockService;

    @Test
    void shouldReturnCreatedBlockWithAreaAndBoulders() {
        //Arrange
        Block b = new Block();
        Area a = new Area();

        Long block_id = 1L;
        b.setId(block_id);
        b.setBlockName("Avalonia Cave");
        b.setStoneType("Granite");
        b.setArea(a);

        List<Boulder> boulders = new ArrayList<>();
        boulders.add(new Boulder(1L));
        boulders.add(new Boulder(2L));
        b.setBoulders(boulders);

        when(blockRepository.findById(anyLong())).thenReturn(Optional.of(b));

        //Act
        BlockDto result = blockService.getBlockById(b.getId());

        //Assert
        assertEquals("Avalonia Cave", result.blockName);
        assertEquals("Granite", result.stoneType);
        assertEquals(1L, result.area_id);
        assertEquals(boulders, b.getBoulders());
    }

    @Test
    void shouldReturnAllCreatedBlockWithAreaAndBoulders() {
        //Arrange
        Block b = new Block();
        Area a = new Area();

        Long block_id = 1L;
        b.setId(block_id);
        b.setBlockName("Avalonia Cave");
        b.setStoneType("Granite");
        b.setArea(a);

        List<Boulder> boulders = new ArrayList<>();
        boulders.add(new Boulder(1L));
        boulders.add(new Boulder(2L));
        b.setBoulders(boulders);

        when(blockRepository.findById(anyLong())).thenReturn(Optional.of(b));

        //Act
        BlockDto result = blockService.getBlockById(b.getId());

        //Assert
        assertEquals("Avalonia Cave", result.blockName);
        assertEquals("Granite", result.stoneType);
        assertEquals(1L, result.area_id);
        assertEquals(boulders, b.getBoulders());
    }

    @Test
    void shouldReturnListOfBlocks() {
        // Arrange
        List<Block> blocks = new ArrayList<>();
        Block block1 = new Block();
        block1.setId(1L);
        block1.setBlockName("Block 1");
        block1.setStoneType("Type 1");
        blocks.add(block1);

        Block block2 = new Block();
        block2.setId(2L);
        block2.setBlockName("Block 2");
        block2.setStoneType("Type 2");
        blocks.add(block2);

        when(blockRepository.findAll()).thenReturn(blocks);

        // Act
        List<BlockDto> result = blockService.getAllBlocks();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        BlockDto resultBlock1 = result.get(0);
        assertEquals(1L, resultBlock1.getId());
        assertEquals("Block 1", resultBlock1.getBlockName());
        assertEquals("Type 1", resultBlock1.getStoneType());

        BlockDto resultBlock2 = result.get(1);
        assertEquals(2L, resultBlock2.getId());
        assertEquals("Block 2", resultBlock2.getBlockName());
        assertEquals("Type 2", resultBlock2.getStoneType());
    }

    @Test
    void shouldReturnBlockById() {
        // Arrange
        Block block = new Block();
        block.setId(1L);
        block.setBlockName("Block 1");
        block.setStoneType("Type 1");

        when(blockRepository.findById(1L)).thenReturn(Optional.of(block));

        // Act
        BlockDto result = blockService.getBlockById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Block 1", result.getBlockName());
        assertEquals("Type 1", result.getStoneType());
    }

    @Test
    void getBlockById_NonexistentId_ThrowsException() {
        // Arrange
        when(blockRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> blockService.getBlockById(1L));
    }

    @Test
    void shouldReturnUpdatedBlock() {
        // Arrange
        Block existingBlock = new Block();
        existingBlock.setId(1L);
        existingBlock.setBlockName("Block 1");
        existingBlock.setStoneType("Type 1");

        when(blockRepository.findById(1L)).thenReturn(Optional.of(existingBlock));

        BlockDto updatedBlockDto = new BlockDto();
        updatedBlockDto.setBlockName("Updated Block");
        updatedBlockDto.setStoneType("Updated Type");

        // Act
        BlockDto result = blockService.updateBlock(1L, updatedBlockDto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Block", result.getBlockName());
        assertEquals("Updated Type", result.getStoneType());
    }

    @Test
    void shouldReturnException() {
        // Arrange
        when(blockRepository.findById(1L)).thenReturn(Optional.empty());

        BlockDto updatedBlockDto = new BlockDto();
        updatedBlockDto.setBlockName("Updated Block");
        updatedBlockDto.setStoneType("Updated Type");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> blockService.updateBlock(1L, updatedBlockDto));
    }

    @Test
    void shouldDeleteBlockById() {
        // Arrange
        doNothing().when(blockRepository).deleteById(1L);

        // Act
        assertDoesNotThrow(() -> blockService.deleteBlockById(1L));

        // Assert
        verify(blockRepository, times(1)).deleteById(1L);
    }
}
