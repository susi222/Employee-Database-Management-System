package com.web.daoImp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.web.dao.EmployeeDAO;
import com.web.database.Myconnect;
import com.web.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public boolean updateEmployee(String id, String name, String email, String mobile, String designation, String gender, String course, InputStream imgStream) {
        String query = "UPDATE t_Employee SET f_Name = ?, f_Email = ?, f_Mobile = ?, f_Designation = ?, f_gender = ?, f_Course = ?"
                     + (imgStream != null ? ", f_Image = ?" : "") + " WHERE f_Id = ?";

        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, name);
            psmt.setString(2, email);
            psmt.setString(3, mobile);
            psmt.setString(4, designation);
            psmt.setString(5, gender);
            psmt.setString(6, course);

            int parameterIndex = 7;
            if (imgStream != null) {
                psmt.setBlob(parameterIndex++, imgStream);
            }

            psmt.setString(parameterIndex, id);

            int rowsAffected = psmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean updateEmployee(Employee employee, InputStream imgStream) {
    	
    	
    	
        String query;
        boolean result = false;

        // Check if an image is being updated or not
        if (imgStream != null) {
            query = "UPDATE t_Employee SET f_Name = ?, f_Designation = ?, f_Gender = ?, f_Course = ?, f_Image = ? WHERE f_Id = ?";
            System.out.println("with");
        } else {
            query = "UPDATE t_Employee SET f_Name = ?, f_Designation = ?, f_Gender = ?, f_Course = ? WHERE f_Id = ?";
            System.out.println("with out");
        }

        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            // Set employee information
            psmt.setString(1, employee.getName());
            psmt.setString(2, employee.getDesignation());
            psmt.setString(3, employee.getGender());
            psmt.setString(4, employee.getCourses());

            // If there's an image stream, set it; otherwise, exclude it from the query
            if (imgStream != null) {
                psmt.setBlob(5, imgStream); // Set image stream
                psmt.setString(6, employee.getId()); // Set employee ID
            } else {
                psmt.setString(5, employee.getId()); // Set employee ID
            }

            System.out.println("reached in second sub 2");
            int rowsAffected = psmt.executeUpdate();
            result = rowsAffected > 0;
            System.out.println(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    
    
    
    
    
    
    

    @Override
    public Employee getEmployeeById(String id) {

        Employee employee = new Employee();;
        String query = "SELECT * FROM t_Employee WHERE f_Id = ?";
        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query)) {
             
            psmt.setString(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    employee.setId(rs.getString("f_Id"));
                    employee.setName(rs.getString("f_Name"));
                    employee.setEmail(rs.getString("f_Email"));
                    employee.setMobile(rs.getString("f_Mobile"));
                    employee.setDesignation(rs.getString("f_Designation"));
                    employee.setGender(rs.getString("f_gender"));
                    employee.setCourses(rs.getString("f_Course"));
                    employee.setImageData(rs.getBytes("f_Image"));  // Assuming image is stored as a byte array
                    employee.setId(rs.getString("f_Id"));
                    employee.setCreatedDate(rs.getDate("f_CreateDate"));
                    // Assuming a method to generate image URL if needed
                    employee.setImageUrl(generateImageUrl(employee.getId()));
                    
                  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return employee;
    }

    private String generateImageUrl(String id) {
        // Implement logic to generate image URL based on employee ID if needed
        return "/images/" + id + ".jpg"; // Example URL
    }
    
    
    
    
}
