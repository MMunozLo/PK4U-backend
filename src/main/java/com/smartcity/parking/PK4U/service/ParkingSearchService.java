//package com.smartcity.parking.PK4U.service;
//
//import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
//import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ParkingSearchService {
//
//    @Autowired
//    private ParkingSearchRepository repository;
//
//    public void indexParking(ParkingSearchDocument document) {
//        repository.save(document);
//    }
//
//    public List<ParkingSearchDocument> searchByName(String query) {
//        return repository.findByNameContainingIgnoreCase(query);
//    }
//}

package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//TODO: eliminar esta anotación si no se usa Elasticsearch
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//Hasta aquí
import java.util.List;

@Service
//TODO: eliminar esta anotación si no se usa Elasticsearch
@ConditionalOnProperty(
    prefix = "spring.data.elasticsearch.repositories",
    name = "enabled",
    havingValue = "true",
    matchIfMissing = true
)
//Hasta aquí
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