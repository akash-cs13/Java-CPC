package com.fhdo.test.cases;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fhdo.entities.energy.EnergyStatus;
import com.fhdo.entities.energy.GridElectricity;
import com.fhdo.entities.energy.SolarPanel;
import com.fhdo.entities.energy.WindTurbine;
import com.fhdo.entities.energy.energySources;
import com.fhdo.entities.energy.energyType;
public class EnergySourceTest {

	@DisplayName("Test check for Energy Source")
	@Test
	public void testGetEnergy() {
		energySources testSource = new SolarPanel(450.0);
		assertEquals(450.0, testSource.getAvailableEnergy());
	}
	
	@DisplayName("Test check for Source Type")
	@Test
	public void testTypeEnergy() {
		energySources mytest = new GridElectricity();
		assertEquals(energyType.GRID_ELECTRICITY, mytest.getEnergyType());
		
	}
	
	@DisplayName("Test check for Source Status")
	@Test
	public void testStatusEnergy() {
		energySources mytest = new WindTurbine();
		mytest.setEnergyStatus(EnergyStatus.USEABLE);
		assertEquals(EnergyStatus.USEABLE, mytest.getEnergyStatus());
		
	}
}
