package com.hexaware.entity;

public class User {
		private int userID;
		private String userName;
		private String email;
		private String password;
		private long contactNumber;
		private String address;
				
		public User(int userID, String userName, String email, String password, long contactNumber, String address) {
			this.userID = userID;
			this.userName = userName;
			this.email = email;
			this.password = password;
			this.contactNumber = contactNumber;
			this.address = address;
		}
		
		
		public User() {
		}


		public int getUserID() {
			return userID;
		}
		public void setUserID(int userID) {
			this.userID = userID;
		}
		
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		public long getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(long contactNumber) {
			this.contactNumber = contactNumber;
		}
		
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}


		@Override
		public String toString() {
			return "User [userID=" + userID + ", userName=" + userName + ", email=" + email + ", password=" + password
					+ ", contactNumber=" + contactNumber + ", address=" + address + "]";
		}
}
