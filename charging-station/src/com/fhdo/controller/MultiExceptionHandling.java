package com.fhdo.controller;

public class MultiExceptionHandling {
	public static  void multiException() {
		try {
			int exampleArr[] = new int[2];
			System.out.println("Value: "+ exampleArr[4]/0);
		} catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
			System.err.println("Exception occur:"+ e.getMessage());
		}
	} 
}

