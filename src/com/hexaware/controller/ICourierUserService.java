package com.hexaware.controller;

import com.hexaware.execption.TrackingNumberNotFoundException;

public interface ICourierUserService {
	
	public boolean login(int userID, String password);
	public void placeOrder(int userID);
	public String getOrderStatus(long trackingNumber) throws TrackingNumberNotFoundException;
	public boolean cancelOrder(long trackingNumber) throws TrackingNumberNotFoundException;
	public void getRealTimeLocation();
}
