//package com.example.TopTracker.controllers;
//
//import com.example.TopTracker.dto.AreaDto;
//import com.example.TopTracker.security.JwtService;
//import com.example.TopTracker.service.AreaService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.when;
//
//@WebMvcTest(AreaController.class)
//@ActiveProfiles("test")
//class AreaControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    JwtService jwtService;
//    @MockBean
//    AreaService areaService;
//
//    @Test
//    @WithMockUser(username = "testuser", roles = "ADMIN")
//    void shouldReturnArea() throws Exception {
//        Long id = 1L;
//        AreaDto areaDto = new AreaDto();
//        areaDto.id = id;
//        areaDto.name = "Fontainebleau";
////        areaDto.address = "Frankrijk";
////        areaDto.description = "Amazing woods in France";
//
//        when(areaService.getAreaById(id)).thenReturn(areaDto);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/areas/" + id))
//                        .andDo(MockMvcResultHandlers.print())
//                                .andExpect(MockMvcResultMatchers.status().isOk())
//                                        .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1))
//                                                .andExpect(MockMvcResultMatchers.jsonPath("$.name," is("Fontainebleau")))
//
//    }
//
//}