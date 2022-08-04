package com.example.roomoccupancymanager.service;

import com.example.roomoccupancymanager.config.RoomPriceConfig;
import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OccupancyManagerService {

    private final RoomPriceConfig roomPriceConfig;

    @Autowired
    public OccupancyManagerService(RoomPriceConfig roomPriceConfig) {
        this.roomPriceConfig = roomPriceConfig;
    }

    public RoomOptimizeResponseDTO getMaxProfit(RoomOptimizeRequestDTO request) {
        Integer freeEconomyRooms = request.economyRoomCount();
        Integer freePremiumRooms = request.premiumRoomCount();
        Double priceThreshold = roomPriceConfig.premiumPriceThreshold();

        List<BigDecimal> offersForRooms = request.offersForRooms();
        offersForRooms.sort(Collections.reverseOrder());

        List<BigDecimal> premiumOffers = new ArrayList<>();
        List<BigDecimal> economyOffers = new ArrayList<>();

        for (BigDecimal o : offersForRooms) {
            if (o.doubleValue() >= priceThreshold) {
                premiumOffers.add(o);
            } else {
                economyOffers.add(o);
            }
        }

        Integer premiumBooked = 0;
        Integer economyBooked = 0;
        BigDecimal economyProfit = BigDecimal.ZERO;
        BigDecimal premiumProfit = BigDecimal.ZERO;

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
