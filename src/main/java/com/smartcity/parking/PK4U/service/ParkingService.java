package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.exception.ParkingsNotFoundException;
import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.repository.ParkingRepository;
import com.smartcity.parking.PK4U.repository.ParkingSpotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static final Logger log = LoggerFactory.getLogger(ParkingService.class);
    private final ParkingRepository parkRepository;
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingService(ParkingRepository parkRepository, ParkingSpotRepository parkingSpotRepository) {
        this.parkRepository = parkRepository;
        this.parkingSpotRepository = parkingSpotRepository;
    }

    //Busca y devuelve un objeto Parking por su ID. Si no existe, lanza una excepción personalizada.
    public Parking getParkingById(String id) {
        log.info("Fetching parking with ID: {}", id);
        return parkRepository.findById(id)
                .orElseThrow(() -> new ParkingsNotFoundException("Parking no encontrado con ID: " + id));
    }

    //Busca y devuelve una lista de ParkingSpot asociados a un Parking por su ID.
    public List<ParkingSpot> getParkingSpotsByParkingId(String parkingId) {
        log.info("Fetching parking spots for parking ID: {}", parkingId);
        return parkingSpotRepository.findByParkingId(parkingId);
    }

    //Calcula y devuelve el estado del Parking, incluyendo el número de plazas ocupadas y libres.
    public Map<String, Long> getParkingStatus(String parkingId) {
        log.info("Fetching parking status for parking ID: {}", parkingId);
        List<ParkingSpot> spots = parkingSpotRepository.findByParkingId(parkingId);
        long occupied = spots.stream().filter(ParkingSpot::isOccupied).count();
        long free = spots.size() - occupied;
        return Map.of("occupied", occupied, "free", free);
    }

    //Actualiza el estado de una plaza de aparcamiento por su ID. Si la plaza no existe, lanza una excepción.
        public ParkingSpot updateSpotStatus(String parkingId, String spotId, boolean occupied) {
            ParkingSpot spot = parkingSpotRepository.findById(spotId)
                    .orElseThrow(() -> new ParkingsNotFoundException("Plaza no encontrada con ID: " + spotId));
            spot.setOccupied(occupied);
            return parkingSpotRepository.save(spot);

        }

    //Devuelve una lista de todos los Parkings disponibles.
    public List<Parking> getAllParkings() {
        return parkRepository.findAll();
    }

    public Map<Integer, Long> getAvailableSpotsPerLevel(String parkingId) {
        List<ParkingSpot> spots = parkingSpotRepository.findByParkingId(parkingId);

        // Filtrar plazas no ocupadas y agrupar por planta (level)
        return spots.stream()
                .filter(spot -> !spot.isOccupied())
                .collect(Collectors.groupingBy(
                        ParkingSpot::getLevel,
                        Collectors.counting()
                ));
    }

}
