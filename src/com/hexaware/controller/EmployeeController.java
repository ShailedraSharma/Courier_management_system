package com.hexaware.controller;

import com.hexaware.entity.Courier;

import com.hexaware.entity.Employee;
import com.hexaware.entity.User;
import com.hexaware.entity.Shipment;
import com.hexaware.dao.CourierServiceDb;
import java.util.*;
import com.hexaware.execption.InvalidEmployeeIdException;



public class EmployeeController implements ICourierAdminService {
		
		private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
		private static final String NUMBERS = "0123456789";
		private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?";
	
		private Map<Long, List<String>> trackingHistory = new HashMap<>();
		
		Employee employee;
		User user;
		
		Scanner sc = new Scanner(System.in);
		CourierServiceDb dao = new CourierServiceDb();
		
		DataValidatorAndFormatter dataValidatorAndFormatter = new DataValidatorAndFormatter();
		
		public long addCourierStaff() {
			employee = new Employee();
			
			System.out.println("Enter employee id");
			long id = sc.nextLong();
			sc.nextLine();
			employee.setEmployeeID(id);
			
			System.out.println("Enter employee name");
			String name = sc.nextLine();
			employee.setEmployeeName(name);
			
			System.out.println("Enter employee email");
			String email = sc.nextLine();
			employee.setEmail(email);
			
			System.out.println("Enter employee contact number");
			long contactNumber = sc.nextLong();
			sc.nextLine();
			employee.setContactNumber(contactNumber);
			
			System.out.println("Enter employee role");
			String role = sc.nextLine();
			employee.setRole(role);
			
			System.out.println("Enter employee salary");
			double salary = sc.nextDouble();
			employee.setSalary(salary);
			
			dao.addEmployee(employee);
			
			return id;
		}
		
		public void assignOrder() throws InvalidEmployeeIdException {
			System.out.println("Enter the courierid: ");
			long courierId = sc.nextLong();
			sc.nextLine();
			System.out.println("Enter the employee id: ");
			long empId = sc.nextLong();
			
			int result = dao.assignOrderToEmployee(courierId, empId);
			
			if(result > 0) {
				System.out.println("Courier with id " + courierId + " successfully assigned to employee with id " + empId);
			}else {
				throw new InvalidEmployeeIdException();
			}
		}
		
		public void getAssignedOrders(long empId) {
			dao.getAssignedOrdersById(empId);
		}
		
		public void addUser() {
			user = new User();
			
			System.out.println("Enter user id: ");
			int customerId = sc.nextInt();
			sc.nextLine();
			user.setUserID(customerId);
			
			String userName;
			do {
				System.out.println("Enter name: ");
				userName = sc.nextLine();
				
			}while(!dataValidatorAndFormatter.validateCustomerData(userName,"name"));
			user.setUserName(userName);
			
			System.out.println("Enter email: ");
			String userEmail = sc.nextLine();
			user.setEmail(userEmail);
			
			long contactNumber;
			do {
				System.out.println("Enter contact number: ");
				contactNumber = sc.nextLong();
				sc.nextLine();
			}
			while(!dataValidatorAndFormatter.validateCustomerData(Long.toString(contactNumber),"number"));	
			user.setContactNumber(contactNumber);
			
			
			String street;
	        String city;
	        String state;
	        String zipCode;
	        System.out.println("Enter street: ");
	        street = sc.nextLine();

	        System.out.println("Enter city: ");
	        city = sc.nextLine();

	        System.out.println("Enter state: ");
	        state = sc.nextLine();

	        System.out.println("Enter zip code: ");
	        zipCode = sc.nextLine();
	        String Address = dataValidatorAndFormatter.formatAddress(street, city, state, zipCode);
	        System.out.println(Address);
	        
	        while (!dataValidatorAndFormatter.validateCustomerData(Address, "address")) {
	            System.out.println("Invalid address format. Please enter again.");
	            
	            System.out.println("Enter street: ");
	            street = sc.nextLine();

	            System.out.println("Enter city: ");
	            city = sc.nextLine();

	            System.out.println("Enter state: ");
	            state = sc.nextLine();

	            System.out.println("Enter zip code: ");
	            zipCode = sc.nextLine();

	            Address = dataValidatorAndFormatter.formatAddress(street, city, state, zipCode);
	        }
	        user.setAddress(Address);
	        
	        String userPassword = passwordGenerator();
			System.out.println("Password generated for the user is : " + userPassword);
			user.setPassword(userPassword);
			
			dao.addUserToSystem(user);
		}

		public void displayOrder() {
			System.out.println("Enter the user ID: ");
			int userId = sc.nextInt();
			sc.nextLine();
			List<Courier> courierList = dao.disaplyOrdersForUser(userId);
			System.out.println(courierList);
		}
		
		public void assignOrUpdateShipment() {
			System.out.println("Enter tracking number of courier: ");
			long trackingNumber = sc.nextLong();
			sc.nextLine();
			String result = dao.getLocation(trackingNumber,"nextLocation");
			if(!result.equals("")) {
				System.out.println("Assiging courier to the next shipment---");
				System.out.println("Enter next location: ");
				String nextLocation = sc.nextLine();
				
				dao.shipmentAssignmentAndUpdate(trackingNumber, result, nextLocation);
				updateTrackingHistory(trackingNumber, nextLocation);

			}else {
				Shipment shipment = new Shipment();
				
				shipment.setTrackingNumber(trackingNumber);
				
				System.out.println("Enter category of the courier: ");
				String category = sc.nextLine();
				shipment.setCategory(category);
				
				System.out.println("Enter current location of the courier: ");
				String currentLocation = sc.nextLine();
				shipment.setCurrentLocation(currentLocation);
	            updateTrackingHistory(trackingNumber, currentLocation);

				
				System.out.println("Enter next location of the courier: ");
				String nextLocation = sc.nextLine();
				shipment.setNextLocation(nextLocation);
	            updateTrackingHistory(trackingNumber, nextLocation);

				
				dao.shipmentAssignmentAndUpdate(shipment);
			}	
		}
		
		public String passwordGenerator() {
			StringBuilder password = new StringBuilder();
			Random random = new Random();
			
			password.append(chooseRandomChar(UPPERCASE_LETTERS, random));
	        password.append(chooseRandomChar(LOWERCASE_LETTERS, random));
	        password.append(chooseRandomChar(NUMBERS, random));
	        password.append(chooseRandomChar(SPECIAL_CHARACTERS, random));	
	        
	        for(int i= 4; i<11; i++) {
	        	String chars = UPPERCASE_LETTERS + LOWERCASE_LETTERS + NUMBERS + SPECIAL_CHARACTERS;
	        	password.append(chooseRandomChar(chars, random));
	        }
	        
	        return password.toString();
		}
		private  char chooseRandomChar(String characters, Random random) {
	        int randomIndex = random.nextInt(characters.length());
	        return characters.charAt(randomIndex);
	    }
		
		
		private void updateTrackingHistory(long trackingNumber, String location) {
			List<String> history = trackingHistory.getOrDefault(trackingNumber, new ArrayList<>());
			history.add(location);
			trackingHistory.put(trackingNumber, history);
		}
		public List<String> getTrackingHistory(long trackingNumber) {
			
	        return trackingHistory.getOrDefault(trackingNumber, new ArrayList<>());
	    }
	
}






