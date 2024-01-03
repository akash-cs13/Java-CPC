package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.controller.ChargingLot;
import com.fhdo.controller.TimeManager;
import com.fhdo.controller.energyManager;
import com.fhdo.entities.cars.Car;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;



public class ChargingLotTest {
	@DisplayName("Initalization")
	@Test
	public void testInitCL() {
		ChargingLot testChargingLot = new ChargingLot(12, "1");
		assertEquals(12, testChargingLot.getID());
	}
	
	@DisplayName("Charge Car")
	@Test
	public void testChargigCar() {
		ChargingLot testChargingLot = new ChargingLot(12, "1");
		Car mycar = new Car("Tesla", 50.0, "DE01FP");
		ArrayList<energySources> energySources = new ArrayList<energySources>();
		energySources.add(new SolarPanel(100.0));
		energySources.add(new WindTurbine(300.0));
		energyManager myEnergyManager = new energyManager(energySources);
		testChargingLot.chargeCar(mycar,myEnergyManager,new TimeManager());
		assertEquals(false, testChargingLot.getisAvailable());
	}
}
