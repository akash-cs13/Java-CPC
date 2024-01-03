package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.controller.energyManager;
import com.fhdo.controller.weatherCondition;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.weather.weatherType;

public class WeatherConditionTest {
	@DisplayName("Test check for Weather Condition")
	@Test
	public void testInitWeather() {
		ArrayList<energySources> energySources = new ArrayList<energySources>();
		energySources.add(new SolarPanel(100.0));
		energySources.add(new WindTurbine(300.0));
		energyManager myEnergyManager = new energyManager(energySources);
		weatherCondition testWC = new weatherCondition(weatherType.RAIN, myEnergyManager, "1");
		assertEquals(weatherType.RAIN, testWC.getCurrentWeather());
	}
	
	@DisplayName("Test check for Setting Weather Condition")
	@Test
	public void testSetWeather() {
		ArrayList<energySources> energySources = new ArrayList<energySources>();
		energySources.add(new SolarPanel(100.0));
		energySources.add(new WindTurbine(300.0));
		energyManager myEnergyManager = new energyManager(energySources);
		weatherCondition testWC = new weatherCondition(weatherType.RAIN, myEnergyManager, "1");
		testWC.setCurrentWeather(weatherType.STORM);
		assertEquals(weatherType.STORM, testWC.getCurrentWeather());
	}
	
	@DisplayName("Test check for Weather Condition Simulation")
	@Test
	public void testSimulWeather() {
		ArrayList<energySources> energySources = new ArrayList<energySources>();
		energySources.add(new SolarPanel(100.0));
		energySources.add(new WindTurbine(300.0));
		energyManager myEnergyManager = new energyManager(energySources);
		weatherCondition testWC = new weatherCondition(weatherType.RAIN, myEnergyManager, "1");
		testWC.weatherSimulation(weatherType.SUN);
		assertEquals(weatherType.SUN, testWC.getCurrentWeather());
	}
}
