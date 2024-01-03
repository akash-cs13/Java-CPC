package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.controller.energyManager;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;


public class EnergyManagerTest {

	@DisplayName("Test check for TotalEnergy")
	@Test
	public void testTotalEnergy() {
		ArrayList<energySources> energySources = new ArrayList<energySources>();
		energySources.add(new SolarPanel(100.0));
		energySources.add(new WindTurbine(300.0));
		energyManager testEnergyManager = new energyManager(energySources);
		testEnergyManager.calculateTotalEnergy();
		assertEquals(400.0,testEnergyManager.getTotalEnergy());
	}
	
}

