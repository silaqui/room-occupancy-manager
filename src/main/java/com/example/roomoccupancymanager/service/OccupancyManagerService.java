package com.example.roomoccupancymanager.service;

import com.example.roomoccupancymanager.config.RoomPriceConfig;
import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OccupancyManagerService {

    private final RoomPriceConfig roomPriceConfig;

    @Autowired
    public OccupancyManagerService(RoomPriceConfig roomPriceConfig) {
        this.roomPriceConfig = roomPriceConfig;
    }

    public RoomOptimizeResponseDTO getMaxProfit(RoomOptimizeRequestDTO request) {
        var freeEconomyRooms = request.economyRoomCount();
        var freePremiumRooms = request.premiumRoomCount();
        var priceThreshold = roomPriceConfig.premiumPriceThreshold();

        var premiumOffers = new ArrayList<BigDecimal>();
        var economyOffers = new ArrayList<BigDecimal>();

        request.offersForRooms()
                .stream()
                .sorted(Comparator.reverseOrder())
                .forEach(o -> {
                    if (o.compareTo(priceThreshold) >= 0) {
                        premiumOffers.add(o);
                    } else {
                        economyOffers.add(o);
                    }
                });

        var premiumBooked = 0;
        var economyBooked = 0;
        var economyProfit = BigDecimal.ZERO;
        var premiumProfit = BigDecimal.ZERO;

        for (BigDecimal p : premiumOffers) {
            if (freePremiumRooms > 0) {
                --freePremiumRooms;
                ++premiumBooked;
                premiumProfit = premiumProfit.add(p);
            }
        }

        int missingEconomyRooms = economyOffers.size() - freeEconomyRooms;
        while (missingEconomyRooms > 0 && freePremiumRooms > 0) {
            --freePremiumRooms;
            ++premiumBooked;
            premiumProfit = premiumProfit.add(economyOffers.get(0));
            economyOffers.remove(0);
        }

        for (BigDecimal p : economyOffers) {
            if (freeEconomyRooms > 0) {
                --freeEconomyRooms;
                ++economyBooked;
                economyProfit = economyProfit.add(p);
            }
        }

        return new RoomOptimizeResponseDTO(economyBooked, premiumBooked, economyProfit, premiumProfit);
    }
}
