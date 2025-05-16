package com.smartcity.parking.PK4U.repository;
import com.smartcity.parking.PK4U.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkRepository extends MongoRepository<Parking, String> {
}

