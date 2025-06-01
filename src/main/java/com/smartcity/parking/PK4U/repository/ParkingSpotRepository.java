package com.smartcity.parking.PK4U.repository;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ParkingSpotRepository extends MongoRepository<ParkingSpot, String> {
    List<ParkingSpot> findByParkingId(String parkingId);
    List<ParkingSpot> findByParkingIdAndLevel(String parkingId, int level);
    List<ParkingSpot> findByParkingIdAndOccupied(String parkingId, boolean occupied);
    List<ParkingSpot> findByParkingIdAndLevelAndOccupied(String parkingId, int level, boolean occupied);
    List<ParkingSpot> findByOccupied(boolean occupied);
    List<ParkingSpot> findByLevel(int level);
    List<ParkingSpot> findBySpotNumber(int spotNumber);
}
