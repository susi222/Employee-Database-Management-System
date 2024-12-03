package com.web.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.web.dao.CreateEmployeeDao;
import com.web.database.Myconnect;
import com.web.models.Employee;

public class CreateEmployeeDaoImpl implements CreateEmployeeDao {
    private String validate = "SELECT COUNT(*) FROM t_Employee WHERE f_Email = ?";
    private String query = "INSERT INTO t_Employee (f_Image, f_Name, f_Email, f_Mobile, f_Designation, f_gender, f_Course, f_Createdate) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
    private Connection con;
    private PreparedStatement psmt;
    private ResultSet res;

    @Override
    public boolean insertEmployeeData(Employee employee) {
        try {
            con = Myconnect.connect();
            psmt = con.prepareStatement(query);

            // Insert image as binary data (BLOB)
            psmt.setBytes(1, employee.getImageData());
            psmt.setString(2, employee.getName());
            psmt.setString(3, employee.getEmail());
            psmt.setString(4, employee.getMobile());
            psmt.setString(5, employee.getDesignation());
            psmt.setString(6, employee.getGender());
            psmt.setString(7, employee.getCourses());

            int x = psmt.executeUpdate();
            if (x != 0) {
                System.out.println(x + " row/s inserted successfully!");
                return true;
            } else {
                System.out.println("Insertion failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        try {
            con = Myconnect.connect();
            psmt = con.prepareStatement(validate);
            psmt.setString(1, email);

            res = psmt.executeQuery();

            if (res.next()) {
                int count = res.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
