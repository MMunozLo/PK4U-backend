package com.smartcity.parking.PK4U.service;

import com.smartcity.parking.PK4U.exception.ParkingsNotFoundException;
import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.model.ParkingSpot;
import com.smartcity.parking.PK4U.model.dto.LevelInfoDTO;
import com.smartcity.parking.PK4U.model.dto.ParkingDetailsDTO;
import com.smartcity.parking.PK4U.model.dto.ParkingSummaryDTO;
import com.smartcity.parking.PK4U.model.dto.SpotDTO;
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

    // Devuelve una lista de todos los parkings en formato de resumen (ParkingSummaryDTO)
    public List<ParkingSummaryDTO> getAllParkingsSummary() {
        log.info("Fetching all parkings for summary view.");
        List<Parking> parkings = parkRepository.findAll();

        return parkings.stream()
                .map(this::mapToParkingSummaryDTO)
                .collect(Collectors.toList());
    }

    // Devuelve los detalles completos de un parking (ParkingDetailsDTO)
    public ParkingDetailsDTO getParkingDetailsById(String parkingId) {
        log.info("Fetching details for parking with ID: {}", parkingId);
        Parking parking = parkRepository.findById(parkingId)
                .orElseThrow(() -> new ParkingsNotFoundException("Parking no encontrado con ID: " + parkingId));

        List<ParkingSpot> spots = parkingSpotRepository.findByParkingId(parkingId);
        return mapToParkingDetailsDTO(parking, spots);
    }

    // Devuelve todas las plazas de un parking como una lista de SpotDTO
    public List<SpotDTO> getAllSpotsByParking(String parkingId) {
        log.info("Fetching all spots for parking ID: {}", parkingId);
        return parkingSpotRepository.findByParkingId(parkingId).stream()
                .map(this::mapToSpotDTO)
                .collect(Collectors.toList());
    }

    // Devuelve las plazas de la planta en específico de un parking como una lista de SpotDTO
    public List<SpotDTO> getSpotsByParkingAndLevel(String parkingId, int level) {
        log.info("Fetching spots for parking ID: {} and level: {}", parkingId, level);

        return parkingSpotRepository.findByParkingIdAndLevel(parkingId, level).stream()
                .map(this::mapToSpotDTO)
                .collect(Collectors.toList());
    }

    //Actualiza el estado de una plaza de aparcamiento por su ID. Si la plaza no existe, lanza una excepción.
    // A este darle una vuelta cuando estemos con el Simulador
    public ParkingSpot updateSpotStatus(String parkingId, String spotId, boolean occupied) {
        ParkingSpot spot = parkingSpotRepository.findById(spotId)
                .orElseThrow(() -> new ParkingsNotFoundException("Plaza no encontrada con ID: " + spotId));
        spot.setOccupied(occupied);
        return parkingSpotRepository.save(spot);
    }


    /*
     * MÉTODOS PRIVADOS DE MAPEO
     */

    private ParkingSummaryDTO mapToParkingSummaryDTO(Parking parking) {
        // Obtenemos el número total de plazas para este parking
        long totalSpots = parkingSpotRepository.countByParkingId(parking.getId());

        return new ParkingSummaryDTO(
                parking.getId(),
                parking.getName(),
                parking.getAddress(),
                parking.getLatitude(),
                parking.getLongitude(),
                (int) totalSpots
        );
    }

    private ParkingDetailsDTO mapToParkingDetailsDTO(Parking parking, List<ParkingSpot> spots) {
        Map<Integer, List<ParkingSpot>> spotsByLevel = spots.stream()
                .collect(Collectors.groupingBy(ParkingSpot::getLevel));

        List<LevelInfoDTO> levelsInfo = spotsByLevel.entrySet().stream()
                .map(entry -> {
                    int level = entry.getKey();
                    List<ParkingSpot> levelSpots = entry.getValue();
                    long freeSpots = levelSpots.stream().filter(s -> !s.isOccupied()).count();

                    return new LevelInfoDTO(
                            "level_" + level, // ID único para la planta
                            "Planta " + level, // Nombre para mostrar
                            levelSpots.size(),
                            (int) freeSpots
                    );
                }).collect(Collectors.toList());

        return new ParkingDetailsDTO(
                parking.getId(),
                parking.getName(),
                parking.getAddress(),
                parking.getLatitude(),
                parking.getLongitude(),
                parking.getPrice(),
                spots.size(),
                parking.getLevels(),

                levelsInfo
        );
    }

    private SpotDTO mapToSpotDTO(ParkingSpot spot) {
        return new SpotDTO(
                spot.getId(),
                spot.getParkingId(),
                spot.getSpotNumber(),
                spot.getLevel(),
                spot.isOccupied()
        );
    }

}
