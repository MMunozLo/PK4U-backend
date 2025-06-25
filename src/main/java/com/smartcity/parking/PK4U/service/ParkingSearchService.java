package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ParkingSearchService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ParkingSearchRepository parkingSearchRepository;

    @Autowired
    public ParkingSearchService(ElasticsearchOperations elasticsearchOperations, ParkingSearchRepository parkingSearchRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.parkingSearchRepository = parkingSearchRepository;
    }

 public void index (ParkingSearchDocument doc){
        parkingSearchRepository.save(doc);
        System.out.println("Documento indexado: " + doc);
 }

    public List<ParkingSearchDocument> searchByName(String name) {
        Criteria criteria = new Criteria("name").contains(name.toLowerCase());
        CriteriaQuery query = new CriteriaQuery(criteria);
        SearchHits<ParkingSearchDocument> hits = elasticsearchOperations.search(query, ParkingSearchDocument.class);

        return hits.getSearchHits()
                .stream()
                .map(hit -> hit.getContent())
                .collect(Collectors.toList());
    }

    public ParkingSearchDocument convertToSearchDocument(Parking parking) {
        ParkingSearchDocument doc = new ParkingSearchDocument();
        doc.setId(parking.getId());
        doc.setName(parking.getName());
        doc.setAddress(parking.getAddress());
        doc.setPrice(parking.getPrice());
        return doc;
    }

}
