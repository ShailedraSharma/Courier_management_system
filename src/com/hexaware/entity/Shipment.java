package com.hexaware.entity;

public class Shipment {
	private long trackingNumber;
	private String category;
	private String currentLocation;
	private String nextLocation;
	
	public Shipment(long trackingNumber, String category, String currentLocation, String nextLocation) {
		super();
		this.trackingNumber = trackingNumber;
		this.category = category;
		this.currentLocation = currentLocation;
		this.nextLocation = nextLocation;
	}

	public Shipment() {
		// TODO Auto-generated constructor stub
	}

	public long getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(long trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getNextLocation() {
		return nextLocation;
	}

	public void setNextLocation(String nextLocation) {
		this.nextLocation = nextLocation;
	}
}
