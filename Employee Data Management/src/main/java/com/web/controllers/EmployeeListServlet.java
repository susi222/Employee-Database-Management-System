package com.web.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.daoImp.EmployeeDAOImpl;
import com.web.daoImp.EmployeeListDAOImpl;
import com.web.models.Employee;

@WebServlet("/EmployeeList")
public class EmployeeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EmployeeListDAOImpl employeeListDAO = new EmployeeListDAOImpl(); // Ensure implementation class is used

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchKeyword = request.getParameter("search");
        List<Employee> employeeList = null;
        int totalCount = 0;

        try {
        	EmployeeListDAOImpl el=new EmployeeListDAOImpl();
            employeeList = el.getEmployees(searchKeyword) ;
            employeeList = el.getAllEmployees() ;
            totalCount = el.getTotalCount();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception and set error message
        }

        request.setAttribute("employeeList", employeeList);
        request.setAttribute("totalCount", totalCount);
        
        request.getRequestDispatcher("EmployeeList.jsp").include(request, response);
    }
}
