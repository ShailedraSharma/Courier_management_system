package com.hexaware.controller;

import java.util.List;

import com.hexaware.execption.InvalidEmployeeIdException;

public interface ICourierAdminService {
	public long addCourierStaff();
	public void assignOrder() throws InvalidEmployeeIdException;
	public void getAssignedOrders(long empId);
	public void addUser();
	public void displayOrder();
	public void assignOrUpdateShipment();
	public List<String> getTrackingHistory(long trackingNumber);
}
