package com.example.roomoccupancymanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("hotel.room-prices")
@ConstructorBinding
public record RoomPriceConfig(
        Double premiumPriceThreshold
) {
}
