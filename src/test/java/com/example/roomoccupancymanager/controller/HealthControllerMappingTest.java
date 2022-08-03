package com.example.roomoccupancymanager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HealthControllerMappingTest {

    private final HealthController tested = mock(HealthController.class);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(tested)
            .build();

    @Test
    public void healthRoutingTest() throws Exception {
        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/health"));

        // Then
        verify(tested).health();
    }
}
