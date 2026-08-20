package com.example.elektrostorage.component;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ComponentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllComponentsReturns200() throws Exception {
        mockMvc.perform(get("/components"))
                .andExpect(status().isOk());
    }

    @Test
    void createComponentReturns200() throws Exception {

        String json = """
            {
              "internalNumber": 2001,
              "name": "Test Component",
              "externalPartNumber": "TEST-001",
              "discontinued": false
            }
            """;

        mockMvc.perform(post("/components")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void discontinueComponentReturns200() throws Exception {
        mockMvc.perform(put("/components/1/discontinue"))
                .andExpect(status().isOk());
    }
}