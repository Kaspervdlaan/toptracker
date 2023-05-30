package com.example.TopTracker.controllers;

import com.example.TopTracker.dto.AreaDto;
import com.example.TopTracker.dto.BlockDto;
import com.example.TopTracker.service.AreaBlocksService;
import com.example.TopTracker.service.AreaService;
import com.example.TopTracker.service.BlockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class AreaControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AreaService areaService;

    @MockBean
    BlockService blockService;

    @MockBean
    AreaBlocksService areaBlocksService;

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void shouldReturnCreatedArea() throws Exception {
        // Arrange
        AreaDto areaDto = new AreaDto();
        areaDto.setName("Area 1");
        areaDto.setAddress("Address 1");
        areaDto.setDescription("Description 1");

        when(areaService.createArea(any(AreaDto.class))).thenReturn(areaDto);

        String requestJson = "{ \"name\": \"Area 1\", \"address\": \"Address 1\", \"description\": \"Description 1\" }";

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/areas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Area 1"))
                .andExpect(jsonPath("$.address").value("Address 1"))
                .andExpect(jsonPath("$.description").value("Description 1"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void shouldReturnAllAreas() throws Exception {
        // Arrange
        AreaDto areaDto = new AreaDto();
        areaDto.setId(1L);
        areaDto.setName("Test Area");

        given(areaService.getAllAreas()).willReturn(Collections.singletonList(areaDto));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/areas"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(areaDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(areaDto.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void shouldReturnAreaFromId() throws Exception {
        // Arrange
        Long areaId = 1L;
        AreaDto areaDto = new AreaDto();
        areaDto.setId(areaId);
        areaDto.setName("Test Area");

        given(areaService.getAreaById(areaId)).willReturn(areaDto);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/areas/{area_id}", areaId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(areaDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(areaDto.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testAddBlockToArea() throws Exception {
        // Arrange
        Long areaId = 1L;
        Long blockId = 1L;
        AreaDto areaDto = new AreaDto();
        areaDto.setId(areaId);
        areaDto.setName("Test Area");

        given(areaBlocksService.addBlockToArea(areaId, blockId)).willReturn(areaDto);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/areas/{area_id}/blocks/{block_id}", areaId, blockId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(areaDto.getName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testGetAreaBlocks() throws Exception {
        // Arrange
        Long areaId = 1L;
        BlockDto blockDto = new BlockDto();
        blockDto.setId(1L);
        blockDto.setBlockName("Test Block");

        given(areaBlocksService.getAreaBlocks(areaId)).willReturn(Collections.singletonList(blockDto));

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/areas/{area_id}/blocks", areaId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(blockDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].blockName").value(blockDto.getBlockName()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void testDeleteAreaById() throws Exception {
        // Arrange
        Long areaId = 1L;

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/areas/{area_id}", areaId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}