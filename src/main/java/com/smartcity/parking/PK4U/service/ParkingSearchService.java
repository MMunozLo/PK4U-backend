package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSearchService {

    @Autowired
    private ParkingSearchRepository repository;

    public void indexParking(ParkingSearchDocument document) {
        repository.save(document);
    }

    public List<ParkingSearchDocument> searchByName(String query) {
        return repository.findByNameContainingIgnoreCase(query);
    }
}