package com.hexaware.controller;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class DataValidatorAndFormatter {
	DataValidatorAndFormatter(){
		
	}
	
	 public boolean validateCustomerData(String data, String detail) {
		 if ("name".equals(detail)) {
	            String[] nameParts = data.split(" ");
	            for (String part : nameParts) {
	                if (!isAllLetters(part) || !isUpperCaseFirstLetter(part)) {
	                	System.out.println("Invalid name format. Name should contain only letters and be properly capitalized.");
	                	return false;
	                }
	            }
	            return true;
	        }else if ("address".equals(detail)) {
	        	if (!containsOnlyLettersAndDigits(data)) {
	                System.out.println("Invalid address format. Address should contain only letters and digits.");
	                return false;
	            }
	            return true;	
	        }else if ("number".equals(detail)) {
	        	if (!containsOnlyDigits(data) || data.length() != 10) {
	                System.out.println("Invalid phone number format. Phone number should contain only digits and be 10 digits long.");
	                return false;
	            }
	            return true;
	        }else {
	            return false;
	        }
	 }
	 
	 private  boolean isAllLetters(String str) {
	        for (char ch : str.toCharArray()) {
	            if (!Character.isLetter(ch)) {
	                return false;
	            }
	        }
	        return true;
	    }
	 
	 private  boolean isUpperCaseFirstLetter(String str) {
	        return !str.isEmpty() && Character.isUpperCase(str.charAt(0));
	    }
	 
	 private  boolean containsOnlyLettersAndDigits(String str) {
	        for (char ch : str.toCharArray()) {
	            if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) {
	                return false;
	            }
	        }
	        return true;
	    }
	 
	 private  boolean containsOnlyDigits(String str) {
	        for (char ch : str.toCharArray()) {
	            if (!Character.isDigit(ch)) {
	                return false;
	            }
	        }
	        return true;
	    }
	
	public String formatAddress(String street, String city, String state, String zipCode) {
		street = capitalizeFirstLetter(street);
		city = capitalizeFirstLetter(city);
		state = capitalizeFirstLetter(state);
		
		return String.format("%s %s %s %s", street,city,state,zipCode);
	}
	
	public String capitalizeFirstLetter(String str) {
		if(str.isEmpty()){
			return str;
		}
		
		String[] words = str.split(" ");
		StringBuilder result = new StringBuilder();
		
		for(String word : words) {
			if (!word.isEmpty()) {
	            result.append(Character.toUpperCase(word.charAt(0)))
	                  .append(word.substring(1).toLowerCase())
	                  .append(" ");
	        }
		}
		return result.toString().trim();	
	}
	public void generateOrderConfirmationEmail(String senderName, String trackingNumber, String receiverAddress, Date deliveryDate) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(deliveryDate);
		System.out.println( "Dear " + senderName + ",\n\n"
                + "Thank you for your order! Your order tracking number is: " + trackingNumber + ".\n"
                + "Delivery address: " + receiverAddress + "\n"
                + "Expected delivery date: " + dateStr + "\n\n"
                + "We appreciate your business.\n\n"
                + "Sincerely,\nThe Order Confirmation Team");
	}	
}
