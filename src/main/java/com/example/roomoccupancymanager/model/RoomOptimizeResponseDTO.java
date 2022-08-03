package com.example.roomoccupancymanager.model;

import java.math.BigDecimal;

public record RoomOptimizeResponseDTO(
        Integer economyOccupation,
        Integer premiumOccupation,
        BigDecimal economyProfit,
        BigDecimal premiumProfit
) {
}
