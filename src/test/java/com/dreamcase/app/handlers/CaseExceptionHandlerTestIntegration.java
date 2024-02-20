package com.dreamcase.app.handlers;

import com.dreamcase.app.enums.CaseExceptionMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CaseExceptionHandlerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;
        private final static String BASE_URL= "/api/v1/cases";
        private final static String EXPECTED_MESSAGE = "must not be null";
        @Test
        void handleValidationExceptions() throws Exception {
            String requestBody = "{}";

            mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(EXPECTED_MESSAGE));
        }

        @Test
        void handleCaseNotFoundException() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/1"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value(CaseExceptionMessage.CASE_NOT_FOUND_EXCEPTION.getValue()+1));
        }
    }
