package com.example.roomoccupancymanager.controller;

import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import com.example.roomoccupancymanager.service.OccupancyManagerService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RoomControllerTest {

    private final OccupancyManagerService occupancyManagerService = mock(OccupancyManagerService.class);

    private final RoomController tested = new RoomController(occupancyManagerService);

    @Test
    public void optimizeTest() {
        // Given
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(Collections.emptyList(), 1, 1);
        RoomOptimizeResponseDTO result = new RoomOptimizeResponseDTO(1, 1, BigDecimal.ONE, BigDecimal.ONE);

        when(occupancyManagerService.getMaxProfit(any())).thenReturn(result);

        // When
        RoomOptimizeResponseDTO actual = tested.optimize(input);

        // Then
        assertEquals(result, actual);
        verify(occupancyManagerService).getMaxProfit(input);
    }
}
