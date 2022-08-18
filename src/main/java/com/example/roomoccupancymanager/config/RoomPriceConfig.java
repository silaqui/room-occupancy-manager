package com.example.roomoccupancymanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.math.BigDecimal;

@ConfigurationProperties("hotel.room-prices")
@ConstructorBinding
public record RoomPriceConfig(
        BigDecimal premiumPriceThreshold
) {
}
