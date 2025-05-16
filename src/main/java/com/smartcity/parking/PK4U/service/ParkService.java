package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.exception.ParkingNotFoundException;
import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.repository.ParkRepository;
import com.smartcity.parking.PK4U.repository.ParkingSpotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParkService {

    private static final Logger log = LoggerFactory.getLogger(ParkService.class);
    private final ParkRepository parkRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkService(ParkRepository parkRepository, ParkingSpotRepository parkingSpotRepository) {
        this.parkRepository = parkRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public Parking getParkingById(String id) {
        log.info("Fetching parking with ID: {}", id);
        return parkRepository.findById(id)
                .orElseThrow(() -> new ParkingNotFoundException("Parking no encontrado con ID: " + id));
    }

    public List<ParkingSpot> getParkingSpotsByParkingId(String parkingId) {
        log.info("Fetching parking spots for parking ID: {}", parkingId);
        return parkingSpotRepository.findByParkingId(parkingId);
    }

    public Map<String, Long> getParkingStatus(String parkingId) {
        log.info("Fetching parking status for parking ID: {}", parkingId);
        List<ParkingSpot> spots = parkingSpotRepository.findByParkingId(parkingId);
        long occupied = spots.stream().filter(ParkingSpot::isOccupied).count();
        long free = spots.size() - occupied;
        return Map.of("occupied", occupied, "free", free);
    }
}