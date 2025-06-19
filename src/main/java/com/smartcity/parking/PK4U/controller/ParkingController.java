package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.config.RabbitConfig;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.model.ParkingSpotUpdate;
import com.smartcity.parking.PK4U.model.dto.ParkingDetailsDTO;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.model.dto.ParkingSummaryDTO;
import com.smartcity.parking.PK4U.model.dto.SpotDTO;
import com.smartcity.parking.PK4U.service.ParkingSearchService;
import com.smartcity.parking.PK4U.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ComponentScan
@RequestMapping("api/v1/parkings")
@Validated
public class ParkingController {
    private static final Logger log = LoggerFactory.getLogger(ParkingController.class);
    private final ParkingService parkService;
    private final RabbitTemplate rabbitTemplate;

    public ParkingController(ParkingService parkService, RabbitTemplate rabbitTemplate) {
        this.parkService = parkService;
        this.rabbitTemplate = rabbitTemplate;
    }

    // Este endpoint devuelve una lista de todos los parkings disponibles pero en formato resumido
    @GetMapping
    public List<ParkingSummaryDTO> getAllParkings() {
        log.info("Fetching all parkings");
        return parkService.getAllParkingsSummary();
    }

    // Este endpoint devuelve un parking concreto por su ID en formato detallado
    @GetMapping("/{parkingId}")
    public ParkingDetailsDTO getParkingById(@PathVariable("parkingId") String parkingId) {
        log.info("Fetching parking with ID: {}", parkingId);
        return parkService.getParkingDetailsById(parkingId);
    }

    // Nuevo parametro para filtrar por planta
    // Este endpoint devuelve todas las plazas de un parking concreto, pudiendo filtrar por planta
    @GetMapping("/{parkingId}/spots")
    public ResponseEntity<List<SpotDTO>> getParkingSpots(
            @PathVariable("parkingId") String parkingId,
            @RequestParam(required = false) Integer level) {
        log.info("Fetching parking spots for parking ID: {}", parkingId);

        List<SpotDTO> spots;
        if (level != null) {
            spots = parkService.getSpotsByParkingAndLevel(parkingId, level);
        } else {
            spots = parkService.getAllSpotsByParking(parkingId);
        }
        return ResponseEntity.ok(spots);

        //return parkService.getParkingSpotsByParkingId(parkingId);
    }

    // A este darle una vuelta cuando estemos con el Simulador
    @PutMapping("/{parkingId}/spots/{spotId}")
    public ParkingSpot updateSpotStatus(
            @PathVariable("parkingId") String parkingId,
            @PathVariable("spotId") String spotId,
            @RequestBody ParkingSpot updatedSpot) {
        log.info("Updating spot status for parking ID: {}, Spot ID: {}", parkingId, spotId);
        updatedSpot.setId(spotId);

        // Create a ParkingSpotUpdate object
        ParkingSpotUpdate update = new ParkingSpotUpdate(spotId, parkingId, String.valueOf(updatedSpot.getLevel()), updatedSpot.isOccupied());

        // Send the update to RabbitMQ
        log.info("Sending parking spot update to RabbitMQ: {}", update);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_KEY, update);

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
