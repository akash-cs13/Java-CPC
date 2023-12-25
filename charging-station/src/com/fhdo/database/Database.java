package com.fhdo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fhdo.entities.cars.Car;
import com.fhdo.entities.users.User;

public class Database {
	
	public List<Object[]> fetch(int numberOfResults) {
		List<Object[]> map = new ArrayList<>();
		int i = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:res/input/UserAndCars.db");
			Statement statement = con.createStatement();
			ResultSet carQuery = statement.executeQuery("select * from Cars");
			ResultSet userQuery = statement.executeQuery("select * from Users");
	        while(carQuery.next() && userQuery.next() && i<numberOfResults)
	        {
	        	Car car = new Car(
                        carQuery.getString("brand"),
                        carQuery.getDouble("batteryCapacity"),
                        carQuery.getString("id")
                );
	        	User user = new User(
                        userQuery.getString("name"),
                        userQuery.getInt("id"),
                        userQuery.getString("username"),
                        userQuery.getString("password"),
                        userQuery.getString("role")
                );
	        	Object[] result = {user, car};
	        	map.add(result);
	            i++;
	        }
	        con.close();
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
			
		}
		return map;		
	}
	
	public List<Object[]> fetchRandomly(int numberOfResults){
		List<Object[]> normalfetch = fetch(numberOfResults);
		Collections.shuffle(normalfetch);
		return normalfetch;
	}
}
