package com.smartcity.parking.PK4U.repository;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingSpotRepository extends MongoRepository<ParkingSpot, String> {
    List<ParkingSpot> findByParkingId(String parkingId);

    List<ParkingSpot> findByParkingIdAndLevel(String parkingId, int level);

    long countByParkingId(String parkingId);
}
