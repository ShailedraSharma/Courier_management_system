package com.hexaware.view;

import java.util.*;


import com.hexaware.execption.TrackingNumberNotFoundException;
import com.hexaware.execption.InvalidEmployeeIdException;
import com.hexaware.controller.*;

public class MainClass {
	public static void main(String[] args) {
		ICourierUserService uc = new UserController();
		ICourierAdminService ec = new EmployeeController();

		Scanner sc = new Scanner(System.in);
		String ch = null;
		int choice;

		System.out.println("Welcome! Please select your role to login");
		System.out.println("1.Customer");
		System.out.println("2.Admin");
		System.out.println("Enter you choice: ");
		choice = sc.nextInt();
		sc.nextLine();

		if(choice == 1) {	
			System.out.println("Enter userid: ");
			int userID = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter password: ");
			String password = sc.nextLine();
			if(uc.login(userID, password)) {
				do {
					System.out.println("1. Place order");
					System.out.println("2. View order status");
					System.out.println("3.Cancel order");
					System.out.println("4. Get Live location of order");
					System.out.println("Enter your choice: ");
					choice = sc.nextInt();
					switch (choice) {
					case 1:{
						uc.placeOrder(userID);
						
						break;
					}
					case 2:{
						System.out.println("Enter tracking number: ");
						long trackingNumber = sc.nextLong();
						sc.nextLine();
						try {
						System.out.println("Current status of the order: " + uc.getOrderStatus(trackingNumber));
						}catch(TrackingNumberNotFoundException e) {
							System.out.println(e.toString());
						}
						break;
					}
					case 3:{
						System.out.println("Enter tracking number of the order to cancel: ");
						long trackingNumber = sc.nextLong();
						try{
							if(uc.cancelOrder(trackingNumber)) {
								System.out.println("Order successfully cancelled!");
						}
						}catch(TrackingNumberNotFoundException e) {
							System.out.println(e.toString());
						}
						break;
						}
						
					case 4:{
						uc.getRealTimeLocation();
						break;
					}
					}
					System.out.println("Do you want to continue?: ");
					ch = sc.next();

				}while(ch.equals("y"));
			}else {
				System.out.println("Inavlid username or password!!");
			}
		}
			else if (choice == 2){
				System.out.println("Enter username: ");
				String userName= sc.nextLine();
				System.out.println("Enter password: ");
				String pwd = sc.nextLine();

				if(userName.equals("root") && pwd.equals("root")) {
					System.out.println("hello admin");
					do {
						System.out.println("1.Add employee");
						System.out.println("2.Get assigned orders");
						System.out.println("3.Assign orders to employee");
						System.out.println("4.Add customer");
						System.out.println("5.Display all orders for customer");
						System.out.println("6.Assign or update shipment for courier");
						System.out.println("7.View tracking history");
						System.out.println("Enter your choice:");
						choice = sc.nextInt();
						

						switch (choice) {
						case 1:{
							long id = ec.addCourierStaff();
							sc.nextLine();
							System.out.println("Employee added with id " + id);
							break;

						}
						case 2:{
							System.out.println("Enter the courier staff id number: ");
							long empId = sc.nextLong();
							sc.nextLine();
							ec.getAssignedOrders(empId);	
							break;
						}
						case 3:{
							try {
								ec.assignOrder();
							}catch(InvalidEmployeeIdException e) {
								System.out.println("Error: " + e.toString());
							}
							break;
						}
						case 4:{
							ec.addUser();
							break;
						}
						case 5:{
							ec.displayOrder();
							break;
						}
						case 6:{
							ec.assignOrUpdateShipment();
							break;
						}
						case 7:{
							System.out.println("Enter tracking number: ");
							long trackingNumber = sc.nextLong();
							sc.nextLine();
							List<String> result = ec.getTrackingHistory(trackingNumber);
							if(result.size() == 0) {
								System.out.println("No tracking history found in system!!");
							}
							for(int i=0; i<result.size(); i++) {
								System.out.println("Location " + (i+1) + ": " + result.get(i));
							}
							break;
						}
						
						}
						System.out.println("Do you want to continue?: ");
						ch = sc.next();
					}while(ch.equals("y"));
				}else {
					System.out.println("Invalid username or password!!");

				}
			}
		}

	}
