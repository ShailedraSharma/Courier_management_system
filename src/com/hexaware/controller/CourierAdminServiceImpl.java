package com.hexaware.controller;

import java.util.List;

import com.hexaware.entity.CourierCompany;
import com.hexaware.execption.InvalidEmployeeIdException;

public class CourierAdminServiceImpl extends CourierUserServiceImpl implements ICourierAdminService {

    public CourierAdminServiceImpl(CourierCompany companyObj) {
        super(companyObj);
    }

    @Override
    public long addCourierStaff() {
        return 0; 
    }

    @Override
    public void assignOrder() throws InvalidEmployeeIdException {
    }

    @Override
    public void getAssignedOrders(long empId) {
    }

    @Override
    public void addUser() {
      
    }

    @Override
    public void displayOrder() {
       
    }

    @Override
    public void assignOrUpdateShipment() {
     
    }

	@Override
	public List<String> getTrackingHistory(long trackingNumber) {
		// TODO Auto-generated method stub
		return null;
	}
}