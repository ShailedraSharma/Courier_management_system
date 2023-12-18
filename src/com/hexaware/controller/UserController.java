package com.hexaware.controller;

import java.util.*;

import com.hexaware.execption.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.hexaware.entity.Courier;
import com.hexaware.entity.Payment;
import com.hexaware.dao.CourierServiceDb;


public class UserController implements ICourierUserService{
	
	static long trackingNumber;
	static long courierid ;
	static long paymentid;
	
	CourierServiceDb dao = new CourierServiceDb();
	DataValidatorAndFormatter dataValidatorAndFormatter = new DataValidatorAndFormatter();
	LocationCoordinatesFinder locationCordinateFinder;
	Courier courier;
	Payment payment;
	Scanner input = new Scanner(System.in);
	
	private void updateLastTrackingNumber() {
        trackingNumber = dao.getLastTrackingNumber() + 1;
    }
	
	private void updateLastCourierId() {
        courierid = dao.getLastCourierId() + 1 ;
    }
	
	private void updateLastPaymentId() {
		paymentid = dao.getLastPaymentID() + 1;
	}
	
	public boolean login(int userID, String password){
		if(dao.authenticateUser(userID, password).equals("true")) {
			return true;
		}else {
		return false;
		}
	}
	
	public void placeOrder(int userId) {
		updateLastTrackingNumber();
		updateLastCourierId();
		updateLastPaymentId();
		courier = new Courier();
		locationCordinateFinder = new LocationCoordinatesFinder();
		
		courier.setUserID(userId);
		
		System.out.println("Enter sender's name: ");
		String senderName = input.nextLine();
		courier.setSenderName(senderName);
		
		System.out.println("Enter sender's address: ");
		String senderAddress = input.nextLine();
		courier.setSenderAddress(senderAddress);
		double[] cordinate1 = locationCordinateFinder.getLocationCordinates(senderAddress);
		
		System.out.println("Enter receiver's name: ");
		String receiveraddress = input.nextLine();
		courier.setReceiverName(receiveraddress);
		
		System.out.println("Enter receiver's address: ");
		String receiverAddress = input.nextLine();
		courier.setReceiverAddress(receiverAddress);
		double[] cordinate2 = locationCordinateFinder.getLocationCordinates(receiverAddress);
		
		double distance = calculateDistance(cordinate1[0],cordinate1[1],cordinate2[0],cordinate2[1]);

		System.out.println("Enter weight of the courier: ");
		double weight = input.nextDouble();
		input.nextLine();
		courier.setWeight(weight);
		String category = null;
		if(weight > 0 && weight <20) {
			category = "Light";
		}
		else if(weight> 20 && weight <50) {
			category = "Meduim";
		}
		else {
			category = "Heavy";
		}
		System.out.println("Courier is categorized as: " + category);
		
		
		System.out.println("Enter delivery date(yyyy-MM-dd): ");
		String dateStr = input.nextLine();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = df.parse(dateStr);
			courier.setDeliveryDate(date);
		} catch (ParseException e) {
			System.out.println("Invalid date fromat");
		}
		courier.setStatus("Processing");
		courier.setCourierID(courierid++);
		
		int shippingCost = calculateShippingCost(category, distance);
		System.out.println("The cost for the shipment will be: " + shippingCost + " rs");
		System.out.println("Do you want to pay and confirm and your order? (y/n): ");
		String choice = input.nextLine();
		
		if(choice.equals("y")) 
		{
		System.out.println("Order placed sucessfully!!");
		System.out.println("Do you want to generate order confirmation mail?(y/n) : ");
		choice = input.nextLine();
		if(choice.equals("y")){
			dataValidatorAndFormatter.generateOrderConfirmationEmail(courier.getSenderName(),Long.toString(trackingNumber),courier.getReceiverAddress(),courier.getDeliveryDate());
		}else {
			System.out.println("Thank you for placing your order!");
			System.out.println("Tracking id!:" + trackingNumber);
		}
		courier.setTrackingNumber(trackingNumber++);
		
		dao.addCourier(courier);
		
		
		payment = new Payment();
		payment.setPaymentID(paymentid++);
		payment.setCourierID(courier.getCourierID());
		payment.setAmount(shippingCost);
		try {
			Date date = df.parse(dateStr);
			payment.setPaymentDate(date);
		} catch (ParseException e) {
			System.out.println("Invalid date fromat");
		}
		dao.addPayment(payment);
		
		}else {
			System.out.println("Order not placed!!");
		}
	}
	
	public int calculateShippingCost(String category, double distance) {
		double shippingCost = 0.0;
		
		if(category.endsWith("Light")) {
			double costPerKm = 1.5;
			shippingCost = distance * costPerKm;
			
		}else if(category.equals("Medium")) {
			double costPerKm = 2.0;
			shippingCost = distance * costPerKm;
		}else {
			double costPerKm = 2.5;
			shippingCost = distance * costPerKm;
		}
		return (int) shippingCost;
		
	}
	
	public double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
		double earthRadius = 6371;
        latitude1 = Math.toRadians(latitude1);
        longitude1 = Math.toRadians(longitude1);
        latitude2 = Math.toRadians(latitude2);
        longitude2 = Math.toRadians(longitude2);
        
        
        double diffLat = latitude1 - latitude2;
        double difflong = longitude1 - longitude2;
        
        double a = Math.sin(diffLat / 2) * Math.sin(diffLat / 2) +
                Math.cos(latitude1) * Math.cos(latitude2) *
                Math.sin(difflong / 2) * Math.sin(difflong / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        double distance = earthRadius * c;
        return distance;
	}
	
	public String getOrderStatus(long trackingNumber) throws TrackingNumberNotFoundException {
		String status = dao.getorderByTrackingNumber(trackingNumber);
		if(status == null) {
			throw new TrackingNumberNotFoundException();
		}
		return status;
	}
	
	public boolean cancelOrder(long trackingNumber) throws TrackingNumberNotFoundException{
		int result = dao.cancerOrderByTrackingNumber(trackingNumber);
		if(result > 0) {
			return true;
		}else {
			throw new TrackingNumberNotFoundException();
		}
	}
	
	public void getRealTimeLocation(){
		System.out.println("Enter tracking Number of the courier: ");
		long trackingNumber = input.nextLong();
		String result = dao.getLocation(trackingNumber,"currentLocation");
		if(result.equals("")) {
			System.out.println("Courier is yet to be assign to a shipment. Thank you for your patience!");
		}
		else {
			locationCordinateFinder = new LocationCoordinatesFinder();
			System.out.println("Courier has been assigned to shipment");
			double[] cordinates = locationCordinateFinder.getLocationCordinates(result);
			System.out.println("Location: " + result);
			System.out.println("Latitude: " + cordinates[0]);
			System.out.println("Longitude: " + cordinates[1]);
		}
	}
	
}
