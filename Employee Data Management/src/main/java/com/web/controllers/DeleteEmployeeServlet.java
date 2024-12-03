package com.web.controllers;
import com.web.daoImp.EmployeeListDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    private EmployeeListDAOImpl employeeDAO=new EmployeeListDAOImpl();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("id");

        if (employeeId != null && !employeeId.trim().isEmpty()) {
            boolean success = employeeDAO.deleteEmployee(employeeId);
            
            

            if (success) {
                // Redirect to the employee list page after successful deletion
                response.sendRedirect("EmployeeList");
            } else {
                // Handle deletion failure
                request.setAttribute("error", "Failed to delete employee.");
                request.getRequestDispatcher("EmployeeList.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("EmployeeList");
        }
    }
}

