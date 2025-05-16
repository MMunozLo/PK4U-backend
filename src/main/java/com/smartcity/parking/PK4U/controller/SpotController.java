package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.service.SpotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/spots")
@Validated

public class SpotController {

    private static final Logger log = LoggerFactory.getLogger(SpotController.class);
    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @PutMapping("/{id}")
    public ParkingSpot updateSpotStatus(@PathVariable String id, @RequestBody ParkingSpot updatedSpot) {
        log.info("Updating spot status for ID: {}", id);
        return spotService.updateSpotStatus(id, updatedSpot);
    }
}