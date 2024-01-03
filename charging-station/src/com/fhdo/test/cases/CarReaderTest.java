package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.controller.CarReader;
import com.fhdo.entities.cars.Car;

public class CarReaderTest {
	@DisplayName("Test for IO Error")
	@Test
	public void testIOError() {
		CarReader reader = new CarReader();
		assertEquals(4,reader.readFile("\\res\\input\\Cars.txt").size());
	}
	
	@DisplayName("Test Data")
	@Test
	public void testData() {
		CarReader reader = new CarReader();
	    List<Car> cars = reader.readFile("\\res\\input\\Cars.txt");
	    assertEquals("TeslaS",cars.get(0).getBrand());
	    assertEquals(25.0,cars.get(1).getbatteryFullCapacity());
	    assertEquals("DA0908",cars.get(2).getId());
	    assertEquals(10.0, cars.get(3).getBatteryCurrentCapacity());
	}

}
