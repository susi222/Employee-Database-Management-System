package com.web.daoImp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.web.dao.EmployeeListDAO;
import com.web.database.Myconnect;
import com.web.models.Employee;

public class EmployeeListDAOImpl implements EmployeeListDAO {

    private String keyword;
    
    
    
    
    
    
    

	public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM t_Employee";

        try (Connection conn = Myconnect.connect();
             Statement psmt = conn.createStatement();
             ResultSet rs = psmt.executeQuery(query)) {

            while (rs.next()) {
                Employee employee = new Employee();
                		employee.setId(rs.getString("f_Id"));
                        employee.setName(rs.getString("f_Name"));
                        employee.setEmail(rs.getString("f_Email"));
                        employee.setMobile(rs.getString("f_Mobile"));
                        employee.setDesignation(rs.getString("f_Designation"));
                        employee.setGender(rs.getString("f_gender"));
                        employee.setCourses(rs.getString("f_Course"));
                        employee.setCreatedDate(rs.getDate("f_CreateDate"));
                        // Assuming you handle image data differently, adjust if needed
                        employee.setImageData(rs.getBytes("f_Image"));
                
                
                System.out.println(employee);
                
                
                
                
                // Set the image URL or path if required
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

	
	
	
	
	
	
    public List<Employee> getEmployees(String searchKeyword) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM t_Employee WHERE f_Name LIKE ? OR f_Email LIKE ?";

        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            psmt.setString(1, "%" + keyword + "%");
            psmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Employee employee = new Employee();
                    		employee.setId(rs.getString("f_Id"));
                            employee.setName(rs.getString("f_Name"));
                            employee.setEmail(rs.getString("f_Email"));
                            employee.setMobile(rs.getString("f_Mobile"));
                            employee.setDesignation(rs.getString("f_Designation"));
                            employee.setGender(rs.getString("f_gender"));
                            employee.setCourses(rs.getString("f_Course"));
                            employee.setCreatedDate(rs.getDate("f_CreateDate"));
                            // Assuming you handle image data differently, adjust if needed
                            employee.setImageData(rs.getBytes("f_Image"));

                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public  int getTotalCount() throws SQLException {
        String query = "SELECT COUNT(*) FROM t_Employee";
        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {
            
            if (rs.next()) {
            	
                return rs.getInt(1);
            }
        }
        return 0;
    }

    
    
    
    
    
    
    
    
    

    private static final String DELETE_EMPLOYEE_QUERY = "DELETE FROM t_Employee WHERE f_Id = ?";

    @Override
    public boolean deleteEmployee(String employeeId) {
        boolean isDeleted = false;
        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(DELETE_EMPLOYEE_QUERY)) {
            psmt.setString(1, employeeId);
            int rowsAffected = psmt.executeUpdate();
            isDeleted = (rowsAffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean updateEmployee(Employee employee, InputStream imgStream) {
        String query;
        boolean result = false;

        // Check if an image is being updated or not
        if (imgStream != null) {
            query = "UPDATE t_Employee SET f_Name = ?, f_Designation = ?, f_Gender = ?, f_Course = ?, f_Image = ? WHERE f_Id = ?";
            System.out.println("Updating with image.");
        } else {
            query = "UPDATE t_Employee SET f_Name = ?, f_Designation = ?, f_Gender = ?, f_Course = ? WHERE f_Id = ?";
            System.out.println("Updating without image.");
        }

        try (Connection conn = Myconnect.connect();
             PreparedStatement psmt = conn.prepareStatement(query)) {

            // Set employee information
            psmt.setString(1, employee.getName());
            psmt.setString(2, employee.getDesignation());
            psmt.setString(3, employee.getGender());
            psmt.setString(4, employee.getCourses());

            // Log values to verify correctness
            System.out.println("Updating employee: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Designation: " + employee.getDesignation());
            System.out.println("Gender: " + employee.getGender());
            System.out.println("Courses: " + employee.getCourses());

            // If there's an image stream, set it; otherwise, exclude it from the query
            if (imgStream != null) {
                psmt.setBlob(5, imgStream); // Set image stream
                psmt.setString(6, employee.getId()); // Set employee ID
                System.out.println("Image is being updated.");
            } else {
                psmt.setString(5, employee.getId()); // Set employee ID
                System.out.println("No image update.");
            }

            int rowsAffected = psmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
            result = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    

    
    
    
    
    
    
    
    
    
    
    

    @Override
    public Employee getEmployeeById(String id) {
    	
    	System.out.println("reached get employee by id");
    	
    	
        Employee employee = new Employee();
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
                    
                    System.out.println("Created object");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(employee);
        return employee;
    }

    private String generateImageUrl(String id) {
        // Implement logic to generate image URL based on employee ID if needed
        return "/images/" + id + ".jpg"; // Example URL
    }
    
    
    
    
    
    
    

}
