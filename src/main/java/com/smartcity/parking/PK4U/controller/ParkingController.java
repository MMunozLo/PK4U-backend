package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.service.ParkingSearchService;
import com.smartcity.parking.PK4U.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ComponentScan
@RequestMapping("api/v1/parkings")
@Validated
public class ParkingController {
    private static final Logger log = LoggerFactory.getLogger(ParkingController.class);
    private final ParkingService parkService;

    public ParkingController(ParkingService parkService) {
        this.parkService = parkService;
    }

    @GetMapping
    public List<Parking> getAllParkings() {
        log.info("Fetching all parkings");
        return parkService.getAllParkings();
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

    @RestController
    @RequestMapping("/search")
    public static class SearchController {

        private final ParkingSearchService service;

        public SearchController(ParkingSearchService service) {
            this.service = service;
        }

        @GetMapping
        public List<ParkingSearchDocument> search(@RequestParam String query) {
            return service.searchByName(query);
        }
    }


}