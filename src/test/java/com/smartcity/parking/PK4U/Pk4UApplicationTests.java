package com.smartcity.parking.PK4U;

import com.smartcity.parking.PK4U.model.Parking;
import com.smartcity.parking.PK4U.repository.ParkingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Pk4UApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ParkingRepository parkingRepository;

	@Test
	void testMongoConnection() {
		Parking parking = new Parking();
		parking.setName("Test Parking");
		parkingRepository.save(parking);

		assertThat(parkingRepository.findByName("Test Parking")).isNotEmpty();
	}

}
