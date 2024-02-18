package com.dreamcase.app.controllers;

import com.dreamcase.app.dto.CaseDto;
import com.dreamcase.app.services.CaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class CaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaseService caseService;

    private CaseDto testCase;
    private final static String BASE_URL = "/api/v1/cases";
    @BeforeEach
    void setUp() {
        testCase = new CaseDto();
        testCase.setId(1L);
        testCase.setTitle("Test Case");
        testCase.setDescription("Test Description");
    }

    @Test
    void createCase() throws Exception {
        when(caseService.createCase(any(CaseDto.class))).thenReturn(testCase);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Case\", \"description\": \"Test Description\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void createCaseException() throws Exception {
        when(caseService.createCase(any(CaseDto.class))).thenReturn(testCase);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Case\", \"description\": \"Test Description\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
    @Test
    void readCase() throws Exception {
        when(caseService.readCase(1L)).thenReturn(testCase);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCase.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testCase.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(testCase.getDescription()));
    }

    @Test
    void updateCase() throws Exception {
        when(caseService.updateCase(any(CaseDto.class), any(Long.class))).thenReturn(testCase);

        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Updated Test Case\", \"description\": \"Updated Test Description\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(testCase.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(testCase.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(testCase.getDescription()));
    }

    @Test
    void deleteCase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL+"/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}