package com.test.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getDBConnection(){
		Connection con = null;
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itech7415","root","");
				
				return con;
			} catch (Exception e) {
				// TODO Auto-generated catch block				
				e.printStackTrace();
			}					 
	
		
		return con;		
	}
}
