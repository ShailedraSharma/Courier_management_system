package com.hexaware.controller;

import com.hexaware.entity.Courier;
import com.hexaware.entity.CourierCompany;
import com.hexaware.execption.TrackingNumberNotFoundException;

public class CourierUserServiceImpl implements ICourierUserService {

    private CourierCompany companyObj;

    public CourierUserServiceImpl(CourierCompany companyObj) {
        this.companyObj = companyObj;
    }

    @Override
    public boolean login(int userID, String password) {
        return false;
    }

    @Override
    public void placeOrder(int userID) {
    }

    @Override
    public String getOrderStatus(long trackingNumber) throws TrackingNumberNotFoundException {
		return null;
    }

    @Override
    public boolean cancelOrder(long trackingNumber) {
        return false;
    }

    @Override
    public void getRealTimeLocation() {
    }
}