package com.smartcity.parking.PK4U.repository;

import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ParkingSearchRepository extends ElasticsearchRepository<ParkingSearchDocument, String> {
    List<ParkingSearchDocument> findByNameContainingIgnoreCase(String name);
}