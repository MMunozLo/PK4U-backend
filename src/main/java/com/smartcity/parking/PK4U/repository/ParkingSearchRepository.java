package com.smartcity.parking.PK4U.repository;

import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSearchRepository extends ElasticsearchRepository<ParkingSearchDocument, String> {
    List<ParkingSearchDocument> findByNameContainingIgnoreCase(String name);
}
