package com.web.dao;

import java.io.InputStream;

import com.web.models.Employee;

public interface EmployeeDAO {
    boolean updateEmployee(String id, String name, String email, String mobile, String designation, String gender, String course, InputStream imgStream);
    public boolean updateEmployee(Employee employee, InputStream imgStream);
    public Employee getEmployeeById(String id);
}
