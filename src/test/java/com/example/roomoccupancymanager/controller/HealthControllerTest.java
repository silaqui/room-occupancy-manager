package com.example.roomoccupancymanager.controller;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthControllerTest {

    private final HealthController tested = new HealthController();

    @Test
    public void healthTest() {
        // Given

        // When
        String actual = tested.health();

        // Then
        assertEquals("OK", actual);
    }
}