package com.hexaware.dao;

import com.hexaware.controller.ICourierUserService;
import com.hexaware.entity.CourierCompanyCollection;
import com.hexaware.execption.TrackingNumberNotFoundException;

public class CourierUserServiceCollectionImpl implements ICourierUserService {
    private CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl(CourierCompanyCollection companyObj) {
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