package com.example.roomoccupancymanager.controller;

import com.example.roomoccupancymanager.model.RoomOptimizeRequestDTO;
import com.example.roomoccupancymanager.model.RoomOptimizeResponseDTO;
import com.example.roomoccupancymanager.service.OccupancyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final OccupancyManagerService occupancyManagerService;

    @Autowired
    public RoomController(OccupancyManagerService occupancyManagerService) {
        this.occupancyManagerService = occupancyManagerService;
    }

    @PostMapping("/optimize")
    public RoomOptimizeResponseDTO optimize(
            @RequestBody RoomOptimizeRequestDTO offersForRooms
    ) {
        return occupancyManagerService.getMaxProfit(offersForRooms);
    }
}
