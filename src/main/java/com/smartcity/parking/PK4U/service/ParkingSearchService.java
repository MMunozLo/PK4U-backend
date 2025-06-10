package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSearchService {

    private final ParkingSearchRepository repository;

    public ParkingSearchService(ParkingSearchRepository repository) {
        this.repository = repository;
    }

    public List<ParkingSearchDocument> searchByName(String query) {
        return repository.findByNameContainingIgnoreCase(query);
    }
}
