package com.hexaware.dao;

import java.sql.Connection;





import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import com.hexaware.entity.*;
import com.hexaware.util.DBConnUtil;

import java.util.List;
import java.util.ArrayList;

public class CourierServiceDb {
	Connection connection;
	Statement statement;
	PreparedStatement ps;
	ResultSet rs;
	
	public String authenticateUser(int userID, String password) {
		String result = "false";
		try {
			connection= DBConnUtil.getDbConnection();
			ps = connection.prepareStatement("SELECT * FROM user WHERE userId = ? and password = ?");
			ps.setInt(1, userID);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				result = "true";
			}
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		return result;
	}
	
	public void addCourier(Courier courier) {
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("insert into couriers values(?,?,?,?,?,?,?,?,?,?,?)");
			
			ps.setLong(1, courier.getCourierID());
			ps.setString(2, courier.getSenderName());
			ps.setString(3, courier.getSenderAddress());
			ps.setString(4, courier.getReceiverName());
			ps.setString(5, courier.getReceiverAddress());
			ps.setDouble(6, courier.getWeight());
			ps.setString(7, courier.getStatus());
			ps.setLong(8,courier.getTrackingNumber());
			ps.setInt(9, courier.getUserID());		
			java.util.Date javaDate = courier.getDeliveryDate();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
			ps.setDate(10,sqlDate);
			ps.setNull(11, Types.BIGINT);
			
			int noofrows = ps.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public String getorderByTrackingNumber(long trackingNumber) {
		String status = null;
		try {
			connection= DBConnUtil.getDbConnection();
			ps = connection.prepareStatement("SELECT status FROM couriers WHERE trackingNumber = ?");
			ps.setLong(1,trackingNumber);
			rs = ps.executeQuery();
			if(rs.next()) {
				status = rs.getString("status");
			}
		}catch(SQLException e) {
			System.out.println("Error occured");
		}
		return status;
	}
	
	public int cancerOrderByTrackingNumber(long trackingNumber) {
		int affectedRows = 0;
		try {
			connection= DBConnUtil.getDbConnection();
			ps = connection.prepareStatement("DELETE FROM couriers WHERE trackingNumber = ?");
			ps.setLong(1,trackingNumber);
			
			affectedRows = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}
	
	public void addEmployee(Employee employee) {
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("insert into employees values(?,?,?,?,?,?)");
			
			ps.setLong(1, employee.getEmployeeID());
			ps.setString(2, employee.getEmployeeName());
			ps.setString(3, employee.getEmail());
			ps.setLong(4, employee.getContactNumber());
			ps.setString(5, employee.getRole());
			ps.setDouble(6, employee.getSalary());
			
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " inserted successfully !!!");	
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int assignOrderToEmployee(long courierId, long empId) {
		int rowsAffected = 0;
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("UPDATE couriers SET employeeID = ?, status = 'In Transit' WHERE courierID = ?");
			
			ps.setLong(1, empId);
			ps.setLong(2, courierId);
			
			rowsAffected = ps.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return rowsAffected;
	}
	
	public void getAssignedOrdersById(long empId) {
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("SELECT * FROM couriers WHERE employeeID = ?");
			ps.setLong(1, empId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				long courierID = rs.getLong("courierID");
                String senderName = rs.getString("senderName");
                System.out.println("Courier id: " + courierID + ", sender name: " + senderName);
				}
			}catch (SQLException e) {
	            e.printStackTrace();
	        }
			
	}
	
	public void addUserToSystem(User user) {
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("insert into user values(?,?,?,?,?,?)");
			
			ps.setInt(1, user.getUserID());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setLong(5, user.getContactNumber());
			ps.setString(6, user.getAddress());
		
			int noofrows = ps.executeUpdate();
			System.out.println("User added sucessfully!!");
	}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	public List<Courier> disaplyOrdersForUser(int userId) {
		List<Courier> courierList = new ArrayList<>();
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("select * from couriers where userId = ?");
			ps.setInt(1,userId);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				Courier courier = new Courier();
				courier.setCourierID(rs.getInt("courierID"));
				courier.setSenderName(rs.getString("senderName"));
				courier.setSenderAddress(rs.getString("senderAddress"));
				courier.setReceiverName(rs.getString("receiverName"));
				courier.setReceiverAddress(rs.getString("senderAddress"));
				courier.setWeight(rs.getDouble("weight"));
				courier.setStatus(rs.getString("status"));
				courier.setTrackingNumber(rs.getLong("trackingNumber"));
				courier.setUserID(rs.getInt("userID"));
				courier.setDeliveryDate(rs.getDate("deliveryDate"));
				
				courierList.add(courier);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		return courierList;
	}
	
	public  long getLastTrackingNumber() {
		long lastTrackingNumber = 1000001;
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("select max(trackingNumber) as lastTrackingNumber from couriers");
			rs = ps.executeQuery();
			
			if (rs.next()) {
			    long maxTrackingNumber = rs.getLong("lastTrackingNumber");
			    if (!rs.wasNull()) {
			        lastTrackingNumber = maxTrackingNumber;
			    }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	return lastTrackingNumber;
	}
	
	public  long getLastCourierId() {
		long lastCourierId = 101;
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("select max(courierID) as lastCourierID from couriers");
			rs = ps.executeQuery();
			
			if (rs.next()) {
			    long maxCourierID = rs.getLong("lastCourierID");
			    if (!rs.wasNull()) {
			        lastCourierId = maxCourierID;
			    }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	return lastCourierId;
	}
	
	public  long getLastPaymentID() {
		long lastPaymentID = 1001;
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("select max(PaymentID) as lastPaymentId from payment");
			rs = ps.executeQuery();
			
			if (rs.next()) {
			    long maxPaymentID = rs.getLong("lastPaymentId");
			    if (!rs.wasNull()) {
			        lastPaymentID = maxPaymentID;
			    }
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	return lastPaymentID;
	}
	
	
	public String getLocation(long trackingNumber, String type) {
		if(type.equals("nextLocation")) {
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("SELECT nextLocation FROM shipment WHERE trackingNumber = ?");
			ps.setLong(1, trackingNumber);
			rs = ps.executeQuery();		
			if(rs.next()) {
					return rs.getString("nextLocation");
			}
			}catch (SQLException e) {
		e.printStackTrace();
			}
		}else {
			try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("SELECT currentLocation FROM shipment WHERE trackingNumber = ?");
			ps.setLong(1, trackingNumber);
			rs = ps.executeQuery();		
			if(rs.next()) {
					return rs.getString("currentLocation");
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			}
		return "";
	}

	public void shipmentAssignmentAndUpdate(long trackingNumber, String currentLocation, String nextLocation){
		try {
			connection = DBConnUtil.getDbConnection();
			ps = connection.prepareStatement("UPDATE shipment SET currentLocation = ?, nextLocation = ? WHERE trackingNumber = ?");
			
			ps.setString(1, currentLocation);
            ps.setString(2, nextLocation);
            ps.setLong(3, trackingNumber);
            
            int rowsAffected = ps.executeUpdate();
     
            if (rowsAffected > 0) {
                System.out.println("Shipment details updated successfully. Courier will be assigned to next shipment from " + currentLocation + " to " + nextLocation);
            } else {
                System.out.println("Failed to update shipment details.");
            }
	}catch (SQLException e) {
		e.printStackTrace();
	}
	}
	
	public void shipmentAssignmentAndUpdate(Shipment shipment) {
		try {
			connection = DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("INSERT INTO shipment (trackingNumber, category, currentLocation, nextLocation) VALUES (?, ?, ?, ?)");
			
			ps.setLong(1, shipment.getTrackingNumber());
            ps.setString(2, shipment.getCategory());
            ps.setString(3, shipment.getCurrentLocation());
            ps.setString(4, shipment.getNextLocation());
            
            int numberOfRowsAffected = ps.executeUpdate();
            if(numberOfRowsAffected > 0) {
            	System.out.println("Courier assigned to the next shipment from " + shipment.getCurrentLocation() + " to " + shipment.getNextLocation());
            	}else {
            		System.out.println("Failed to assign courier to shipment");
            	}
            }catch (SQLException e) {
    			e.printStackTrace();
    		}
            
		}
	
	public void addPayment(Payment payment) {
		try {
			connection= DBConnUtil.getDbConnection();
			ps=connection.prepareStatement("insert into payment values(?,?,?,?)");
			
			ps.setLong(1, payment.getPaymentID());
			ps.setLong(2, payment.getCourierID());
			ps.setDouble(3, payment.getAmount());
			
			java.util.Date javaDate = payment.getPaymentDate();
			java.sql.Date sqlDate = new java.sql.Date(javaDate.getTime());
			ps.setDate(4, sqlDate);
		
			ps.executeUpdate();
	}catch (SQLException e) {
		e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}