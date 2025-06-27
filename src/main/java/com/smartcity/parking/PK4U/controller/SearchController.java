package com.smartcity.parking.PK4U.controller;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingRepository;
import com.smartcity.parking.PK4U.service.ParkingSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final ParkingSearchService searchService;

    public SearchController(ParkingSearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public List<ParkingSearchDocument> search(@RequestParam String query) {
        return searchService.searchByName(query);
    }
    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingSearchService parkingSearchService;

    @GetMapping("/index-all")
    public ResponseEntity<String> indexAllParkings() {
        List<Parking> parkings = parkingRepository.findAll();
        for (Parking parking : parkings) {
            ParkingSearchDocument doc = parkingSearchService.convertToSearchDocument(parking);
            parkingSearchService.index(doc);
        }
        return ResponseEntity.ok("Todos los parkings han sido indexados");
    }

    @DeleteMapping("/delete-index")
    public ResponseEntity<String> deleteIndex() {
        searchService.deleteIndex();
        return ResponseEntity.ok("Índice eliminado correctamente.");
    }

}
