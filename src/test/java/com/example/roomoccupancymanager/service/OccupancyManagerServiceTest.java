package com.example.roomoccupancymanager.service;

import com.example.roomoccupancymanager.config.RoomPriceConfig;
import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OccupancyManagerServiceTest {

    private final RoomPriceConfig roomPriceConfig = new RoomPriceConfig(new BigDecimal(100));
    private final OccupancyManagerService tested = new OccupancyManagerService(roomPriceConfig);

    @Test
    public void getMaxProfitTest() {
        // Given
        Integer premiumRooms = 0;
        Integer economyRooms = 0;
        List<BigDecimal> offers = Stream.of(1, 3).map(BigDecimal::valueOf).collect(Collectors.toList());
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(offers, economyRooms, premiumRooms);

        // When
        RoomOptimizeResponseDTO actual = tested.getMaxProfit(input);

        // Then
        assertEquals(0, actual.economyOccupation());
        assertEquals(0, actual.premiumOccupation());
        assertEquals(BigDecimal.valueOf(0), actual.economyProfit());
        assertEquals(BigDecimal.valueOf(0), actual.premiumProfit());
    }

    @Test
    public void getMaxProfitEconomyRoomOnlyTest() {
        // Given
        Integer premiumRooms = 0;
        Integer economyRooms = 1;
        List<BigDecimal> offers = Stream.of(5, 10).map(BigDecimal::valueOf).collect(Collectors.toList());
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(offers, economyRooms, premiumRooms);

        // When
        RoomOptimizeResponseDTO actual = tested.getMaxProfit(input);

        // Then
        assertEquals(1, actual.economyOccupation());
        assertEquals(0, actual.premiumOccupation());
        assertEquals(BigDecimal.valueOf(10), actual.economyProfit());
        assertEquals(BigDecimal.valueOf(0), actual.premiumProfit());
    }

    @Test
    public void getMaxProfitPremiumRoomOnlyTest() {
        // Given
        Integer premiumRooms = 1;
        Integer economyRooms = 0;
        List<BigDecimal> offers = Stream.of(5, 100).map(BigDecimal::valueOf).collect(Collectors.toList());
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(offers, economyRooms, premiumRooms);

        // When
        RoomOptimizeResponseDTO actual = tested.getMaxProfit(input);

        // Then
        assertEquals(0, actual.economyOccupation());
        assertEquals(1, actual.premiumOccupation());
        assertEquals(BigDecimal.valueOf(0), actual.economyProfit());
        assertEquals(BigDecimal.valueOf(100), actual.premiumProfit());
    }

    @Test
    public void getMaxProfitWithUpgradeToPremiumRoomOnlyTest() {
        // Given
        Integer premiumRooms = 2;
        Integer economyRooms = 1;
        List<BigDecimal> offers = Stream.of(50, 100, 5).map(BigDecimal::valueOf).collect(Collectors.toList());
        RoomOptimizeRequestDTO input = new RoomOptimizeRequestDTO(offers, economyRooms, premiumRooms);

        // When
        RoomOptimizeResponseDTO actual = tested.getMaxProfit(input);

        // Then
        assertEquals(1, actual.economyOccupation());
        assertEquals(2, actual.premiumOccupation());
        assertEquals(BigDecimal.valueOf(5), actual.economyProfit());
        assertEquals(BigDecimal.valueOf(150), actual.premiumProfit());
    }

}