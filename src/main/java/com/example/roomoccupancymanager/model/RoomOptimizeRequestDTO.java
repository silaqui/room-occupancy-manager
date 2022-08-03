package com.example.roomoccupancymanager.model;

import java.math.BigDecimal;
import java.util.List;

public record RoomOptimizeRequestDTO(
        List<BigDecimal> offersForRooms,
        Integer economyRoomCount,
        Integer premiumRoomCount
) {
}
