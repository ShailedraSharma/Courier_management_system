package com.hexaware.execption;

public class InvalidEmployeeIdException extends Exception {
	public InvalidEmployeeIdException() {
		
	}
	
	public String toString() {
		return "The given employee ID is not found";
	}
}
