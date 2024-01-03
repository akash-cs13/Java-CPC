package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.entities.cars.Car;

public class CarTest {
	@DisplayName("Test ID")
	@Test
	public void testGetID() {
		Car testCar = new Car("Tesla", 50.0, "DE01FP");
		assertEquals("DE01FP", testCar.getId());
	}
	
	@DisplayName("Test Brand")
	@Test
	public void testGetBrand() {
		Car testCar = new Car("Tesla", 50.0, "DE01FP");
		assertEquals("Tesla", testCar.getBrand());
	}
	
	@DisplayName("Test Battery Capacity")
	@Test
	public void testGetBC() {
		Car testCar = new Car("Tesla", 50.0, "DE01FP");
		assertEquals(50.0, testCar.getbatteryFullCapacity());
	}
	
	@DisplayName("Test Initial Current Battery Capacity")
	@Test
	public void testGetICBC() {
		Car testCar = new Car("Tesla", 50.0, "DE01FP");
		assertEquals(10.0, testCar.getBatteryCurrentCapacity());
	}
	
	@DisplayName("Test Current Battery Capacity after charging")
	@Test
	public void testGetCBCAfterCharging() {
		Car testCar = new Car("Tesla", 50.0, "DE01FP");
		testCar.increaseBatteryCurrentCapacity(10.0);
		assertEquals(20.0, testCar.getBatteryCurrentCapacity());
	}
}
