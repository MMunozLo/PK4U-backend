package com.smartcity.parking.PK4U.listener;

import com.smartcity.parking.PK4U.model.ParkingSpot;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotUpdateListener {

    @RabbitListener(queues = "parking.simulator.queue")
    public void handleSimulation(ParkingSpot update) {
        System.out.println(" Mensaje recibido: " + update);

        // Aquí puedes llamar a tu ParkingService para actualizar la plaza
        //parkingService.updateSpotStatus(update.getParkingId(), update.getId(), update.isOccupied());
    }
}
