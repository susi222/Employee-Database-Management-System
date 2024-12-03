package com.web.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.web.models.Employee;

public interface EmployeeListDAO {
    List<Employee> getEmployees(String searchKeyword) throws SQLException;
    int getTotalCount() throws SQLException;
	boolean deleteEmployee(String employeeId);
	Employee getEmployeeById(String id);
	public boolean updateEmployee(Employee employee, InputStream imgStream);
}

