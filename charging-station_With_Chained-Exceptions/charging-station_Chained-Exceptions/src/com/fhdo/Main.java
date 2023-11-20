package com.fhdo;

import com.fhdo.controller.ChargingStation;
import com.fhdo.model.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		ChargingStation chargingStation = new ChargingStation();
	
	    // Create energy sources
	    EnergySource solarPanel = new SolarPanel();
	    EnergySource windTurbine = new WindTurbine();
	    EnergySource gridElectricity = new GridElectricity();
	
	    // Add energy sources to the charging station
	    chargingStation.addEnergySource(solarPanel);
	    chargingStation.addEnergySource(windTurbine);
	    chargingStation.addEnergySource(gridElectricity);
	
	    // Create cars
	    Car car1 = new Car("Tesla", 300.0);
	    Car car2 = new Car("Nissan Leaf", 1750.0);
	   
	    // Charge cars at the charging station
	    chargingStation.startCharging(car1, solarPanel);
	    chargingStation.startCharging(car2, windTurbine);
	}
}
