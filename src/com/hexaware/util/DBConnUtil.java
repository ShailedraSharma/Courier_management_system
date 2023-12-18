package com.hexaware.util;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class DBConnUtil {
	static Connection con;
	
	public static Connection getDbConnection() {
		
		String fileName = "db.properties";
		Properties props = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			props.load(fis);
			
			String url = props.getProperty("db.url");
			String un = props.getProperty("db.username");
			String pwd = props.getProperty("db.password");
			
			
			con=DriverManager.getConnection(url,un,pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}	
			return con;
		}
	public static void main(String[] args) {
		System.out.println(getDbConnection());
	}
}