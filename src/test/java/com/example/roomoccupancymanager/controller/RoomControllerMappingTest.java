package com.example.roomoccupancymanager.controller;


import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoomControllerMappingTest {

    private final RoomController tested = mock(RoomController.class);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(tested)
            .build();

    @Test
    public void optimizeRoutingTest() throws Exception {
        // Given
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(Collections.emptyList(), 1, 1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(input);

        // When
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/rooms/optimize")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        );

        // Then
        verify(tested).optimize(input);
    }
}
