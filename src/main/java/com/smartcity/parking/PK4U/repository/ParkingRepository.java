package com.smartcity.parking.PK4U.repository;
import com.smartcity.parking.PK4U.model.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ParkingRepository extends MongoRepository<Parking, String> {
    List<Parking> findByName(String name);
    List<Parking> findByAddressContainingIgnoreCase(String address);
    List<Parking> findByLevels(int levels);
    List<Parking> findByLatitudeBetweenAndLongitudeBetween(double latStart, double latEnd, double lonStart, double lonEnd);


}