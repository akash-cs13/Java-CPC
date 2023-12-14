package com.fhdo.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fhdo.entities.cars.Car;;

//Resource Management
public class CarReader {

	private String cwd;
	public CarReader() {
		this.cwd = System.getProperty("user.dir");
	}
	
	public List<Car> readFile(String filePath){
		List<Car> cars = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(cwd+filePath))){
			String line;
			while((line = br.readLine()) != null) {
				String[] words = line.split("-");
				Car car = new Car(words[0],Double.parseDouble(words[1]));
				cars.add(car);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return cars;
	}
}