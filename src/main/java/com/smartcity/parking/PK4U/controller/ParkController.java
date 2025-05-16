package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.service.ParkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parkings")
@Validated

public class ParkController {
    private static final Logger log = LoggerFactory.getLogger(ParkController.class);
    private final ParkService parkService;

    public ParkController(ParkService parkService) {
        this.parkService = parkService;
    }

    @GetMapping("/{id}")
    public Parking getParkingById(@PathVariable String id) {
        log.info("Fetching parking with ID: {}", id);
        return parkService.getParkingById(id);
    }

    @GetMapping("/{id}/spots")
    public List<ParkingSpot> getParkingSpots(@PathVariable String id) {
        log.info("Fetching parking spots for parking ID: {}", id);
        return parkService.getParkingSpotsByParkingId(id);
    }

    @GetMapping("/{id}/status")
    public Map<String, Long> getParkingStatus(@PathVariable String id) {
        log.info("Fetching parking status for parking ID: {}", id);
        return parkService.getParkingStatus(id);
    }
}