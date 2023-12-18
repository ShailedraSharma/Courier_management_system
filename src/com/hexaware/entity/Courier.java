package com.hexaware.entity;
import java.util.Date;

public class Courier {
	private long courierID;
	private String senderName;
	private String senderAddress;
	private String receiverName;
	private String receiverAddress;
	private double weight;
	private String status;
	private long trackingNumber;
	private int userID;
	private Date deliveryDate;

	
	
	public Courier(long courierID, String senderName, String senderAddress, String receiverName, String receiverAddress,
			double weight, String status, long trackingNumber, int userID, Date deliveryDate) {
		this.courierID = courierID;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.receiverName = receiverName;
		this.receiverAddress = receiverAddress;
		this.weight = weight;
		this.status = status;
		this.trackingNumber = trackingNumber;
		this.userID = userID;
		this.deliveryDate = deliveryDate;
	}
	
	
	public Courier() {
	}
	
	
	public long getCourierID() {
		return courierID;
	}
	public void setCourierID(long courierID) {
		this.courierID = courierID;
	}
	
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(long trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public String toString() {
		return "Courier [courierID=" + courierID + ", senderName=" + senderName + ", senderAddress=" + senderAddress
				+ ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", weight=" + weight
				+ ", status=" + status + ", trackingNumber=" + trackingNumber + ", userID=" + userID + ", deliveryDate="
				+ deliveryDate + "]";
	}
}
