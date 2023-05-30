package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.dto.BoulderDto;
import com.example.TopTracker.service.BlockBouldersService;
import com.example.TopTracker.service.BlockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class BlockControllerIntegrationTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BlockService blockService;

    @MockBean
    private BlockBouldersService blockBouldersService;

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void shouldCreateBlock() throws Exception {
        // Arrange
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1L);
        blockDto.setBlockName("Block 1");

        when(blockService.createBlock(any(BlockDto.class))).thenReturn(blockDto);

        String requestJson = "{ \"name\": \"Block 1\" }";

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/blocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.blockName").value("Block 1"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void shouldReturnAllBlocks() throws Exception {
        // Arrange
        BlockDto block1 = new BlockDto();
        block1.setId(1L);
        block1.setBlockName("Block 1");

        BlockDto block2 = new BlockDto();
        block2.setId(2L);
        block2.setBlockName("Block 2");

        List<BlockDto> blockDtos = new ArrayList<>();
        blockDtos.add(block1);
        blockDtos.add(block2);


        when(blockService.getAllBlocks()).thenReturn(blockDtos);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/blocks"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].blockName").value("Block 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].blockName").value("Block 2"));
    }

    @Test
    void shouldReturnBlockById() throws Exception {
        // Arrange
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1L);
        blockDto.setBlockName("Block 1");

        when(blockService.getBlockById(1L)).thenReturn(blockDto);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/blocks/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.blockName").value("Block 1"));
    }

    @Test
    void shouldReturnBouldersForBlock() throws Exception {
        // Arrange
        BoulderDto boulder1 = new BoulderDto();
        boulder1.setId(1L);
        boulder1.setBoulderName("Boulder 1");

        BoulderDto boulder2 = new BoulderDto();
        boulder2.setId(2L);
        boulder2.setBoulderName("Boulder 2");

        List<BoulderDto> boulderDtos = Arrays.asList(boulder1, boulder2);

        when(blockBouldersService.getBlockBoulders(1L)).thenReturn(boulderDtos);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/blocks/1/boulders"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].boulderName").value("Boulder 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].boulderName").value("Boulder 2"));
    }

    @Test
    void shouldAddBoulderToBlock() throws Exception {
        // Arrange
        BoulderDto boulderDto = new BoulderDto();
        boulderDto.setId(1L);
        boulderDto.setBoulderName("Boulder 1");

        BlockDto blockDto = new BlockDto();
        blockDto.setId(1L);
        blockDto.setBlockName("Block 1");

        when(blockBouldersService.addBoulderToBlock(1L, 1L)).thenReturn(blockDto);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/blocks/1/boulders/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.blockName").value("Block 1"));
    }

    @Test
    void shouldUpdateBlockById() throws Exception {
        // Arrange
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1L);
        blockDto.setBlockName("Block 1");

        when(blockService.updateBlock(1L, blockDto)).thenReturn(blockDto);

        String requestJson = "{ \"name\": \"Block 1\" }";

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/blocks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.blockName").value("Block 1"));
    }

    @Test
    void shouldDeleteBlockById() throws Exception {
        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/blocks/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent());
    }
}
