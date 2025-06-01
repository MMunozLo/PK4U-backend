package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.service.ParkingSearchService;
import com.smartcity.parking.PK4U.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parkings")
@Validated
public class ParkingController {
    private static final Logger log = LoggerFactory.getLogger(ParkingController.class);
    private final ParkingService parkService;
    private final ParkingSearchService parkingSearchService;

    public ParkingController(ParkingService parkService, ParkingSearchService parkingSearchService) {
        this.parkService = parkService;
        this.parkingSearchService = parkingSearchService;
    }

    @GetMapping("/{parkingId}")
    public Parking getParkingById(@PathVariable("parkingId") String parkingId) {
        log.info("Fetching parking with ID: {}", parkingId);
        return parkService.getParkingById(parkingId);
    }

    @GetMapping("/{parkingId}/spots")
    public List<ParkingSpot> getParkingSpots(@PathVariable("parkingId") String parkingId) {
        log.info("Fetching parking spots for parking ID: {}", parkingId);
        return parkService.getParkingSpotsByParkingId(parkingId);
    }

    @GetMapping("/{parkingId}/status")
    public Map<String, Long> getParkingStatus(@PathVariable("parkingId") String parkingId) {
        log.info("Fetching parking status for parking ID: {}", parkingId);
        return parkService.getParkingStatus(parkingId);
    }

    @PutMapping("/{parkingId}/spots/{spotId}")
    public ParkingSpot updateSpotStatus(
            @PathVariable("parkingId") String parkingId,
            @PathVariable("spotId") String spotId,
            @RequestBody ParkingSpot updatedSpot) {
        log.info("Updating spot status for parking ID: {}, Spot ID: {}", parkingId, spotId);
        updatedSpot.setId(spotId);
        return parkService.updateSpotStatus(parkingId, spotId, updatedSpot.isOccupied());
    }

    // Endpoint para buscar parkings por nombre
    @GetMapping("/search")
    public List<ParkingSearchDocument> searchParkings(@RequestParam String query) {
        return parkingSearchService.searchByName(query);
    }

}