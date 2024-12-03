package com.web.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Myconnect {
	static Connection con=null;
	private static String url="jdbc:mysql://localhost:3306/mern";
	private static  String user="root";
	private static String pass="root";
	
	public static Connection connect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}

}
