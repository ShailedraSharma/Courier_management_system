package com.hexaware.entity;

public class Employee {
		private long employeeID;
		private String employeeName;
		private String email;
		private long contactNumber;
		private String role;
		private double salary;
		
		public Employee(long employeeID, String employeeName, String email, long contactNumber, String role,
				double salary) {
			this.employeeID = employeeID;
			this.employeeName = employeeName;
			this.email = email;
			this.contactNumber = contactNumber;
			this.role = role;
			this.salary = salary;
		}
		
		public Employee() {
		}

		public long getEmployeeID() {
			return employeeID;
		}
		public void setEmployeeID(long employeeID) {
			this.employeeID = employeeID;
		}
		
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public long getContactNumber() {
			return contactNumber;
		}
		public void setContactNumber(long contactNumber) {
			this.contactNumber = contactNumber;
		}
		
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", email=" + email
					+ ", contactNumber=" + contactNumber + ", role=" + role + ", salary=" + salary + "]";
		}
		
}
