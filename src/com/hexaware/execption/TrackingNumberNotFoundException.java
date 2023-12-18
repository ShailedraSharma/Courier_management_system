package com.hexaware.execption;

public class TrackingNumberNotFoundException extends Exception {
	public TrackingNumberNotFoundException() {
	
	}
	public String toString() {
		return "The given tracking number is not found";
	}

}
