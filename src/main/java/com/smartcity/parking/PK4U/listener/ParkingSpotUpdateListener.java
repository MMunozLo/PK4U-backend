package com.smartcity.parking.PK4U.listener;

import com.smartcity.parking.PK4U.config.RabbitConfig;
import com.smartcity.parking.PK4U.model.ParkingSpotUpdate;
import com.smartcity.parking.PK4U.service.ParkingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotUpdateListener {

    private final ParkingService parkingService;

    public ParkingSpotUpdateListener(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveMessage(ParkingSpotUpdate update) {
        System.out.println("Recibido: " + update);

        parkingService.updateSpotStatus(
                update.getParkingId(),
                update.getId(),
                update.isOccupied()
        );
    }
}
