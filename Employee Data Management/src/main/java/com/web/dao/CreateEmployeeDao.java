package com.web.dao;

import com.web.models.Employee;

public interface CreateEmployeeDao {
    public boolean insertEmployeeData(Employee employee);
    public boolean isEmailDuplicate(String email);
}
