package com.hexaware.entity;

public class Location {
	private long locationID;
	private String locationName;
	private String Address;
	
	public Location(long locationID, String locationName, String address) {
		this.locationID = locationID;
		this.locationName = locationName;
		Address = address;
	}

	public long getLocationID() {
		return locationID;
	}
	public void setLocationID(long locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

	@Override
	public String toString() {
		return "Location [locationID=" + locationID + ", locationName=" + locationName + ", Address=" + Address + "]";
	}
}
