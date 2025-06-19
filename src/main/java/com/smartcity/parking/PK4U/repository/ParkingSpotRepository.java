package com.smartcity.parking.PK4U.repository;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ParkingSpotRepository extends MongoRepository<ParkingSpot, String> {
    List<ParkingSpot> findByParkingId(String parkingId);

    @Query("{ 'parkingId' : ?0 }")
    List<ParkingSpot> findByParkingIdCustom(ObjectId parkingId);

    List<ParkingSpot> findByParkingIdAndLevel(String parkingId, int level);

    @Query("{ 'parkingId': ?0, 'level': ?1 }")
    List<ParkingSpot> findByParkingIdAndLevelCustom(ObjectId parkingId, int level);

    long countByParkingId(String parkingId);

    @Query(value = "{ 'parkingId' : ?0 }", count = true)
    long countByParkingIdCustom(ObjectId parkingId);
}
