package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.repository.ParkingSpotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public ParkingSpot updateSpotStatus(String id, ParkingSpot updatedSpot) {
        ParkingSpot spot = parkingSpotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plaza no encontrada"));
        spot.setOccupied(updatedSpot.isOccupied());
        return parkingSpotRepository.save(spot);
    }
}
