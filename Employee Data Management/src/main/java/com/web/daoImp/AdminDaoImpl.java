package com.web.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.web.dao.AdminDao;
import com.web.database.Myconnect;
import com.web.models.Admin;

public class AdminDaoImpl implements AdminDao {
	
	
	String query = "SELECT * FROM t_login WHERE f_userName = ? AND f_pwd = ?";
	
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;

	@Override
	public boolean validateAdmin(Admin admin) {
		con=Myconnect.connect();
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, admin.getUsername());
			pstmt.setString(2, admin.getPassword());
			
			res = pstmt.executeQuery();
			
			if(res.next()) {
				//System.out.println(res.getString("f_userName")+" "+res.getString("f_pwd"));
				return true;
			}

		} catch (Exception e) {
			System.out.println("Some Error...........");
			e.printStackTrace();
		}

		
		
		
		return false;
	}

	


	
	

}
