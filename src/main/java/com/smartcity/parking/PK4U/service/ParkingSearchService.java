package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.dto.ParkingSearchDocument;
import com.smartcity.parking.PK4U.repository.ParkingSearchRepository;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.*;
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

    public void deleteIndex() {
        elasticsearchOperations.indexOps(ParkingSearchDocument.class).delete();
        System.out.println("Índice eliminado.");
    }

    public List<ParkingSearchDocument> searchByName(String name) {
        Query query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", name).operator(Operator.AND))
                .build();

        SearchHits<ParkingSearchDocument> hits =
                elasticsearchOperations.search(query, ParkingSearchDocument.class);

        return hits.getSearchHits()
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }





    public ParkingSearchDocument convertToSearchDocument(Parking parking) {
        return ParkingSearchDocument.builder()
                .id(parking.getId())
                .name(parking.getName())
                .address(parking.getAddress())
                .totalSpots(parking.getTotalSpots())
                .build();
    }


}
