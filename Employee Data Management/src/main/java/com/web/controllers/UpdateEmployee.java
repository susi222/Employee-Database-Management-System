package com.web.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.web.daoImp.EmployeeListDAOImpl;
import com.web.models.Employee;

@WebServlet("/UpdateEmployee")
@MultipartConfig(
    maxFileSize = 1024 * 1024 * 10,  // Maximum file size (10MB)
    maxRequestSize = 1024 * 1024 * 10 // Maximum request size (10MB)
)
public class UpdateEmployee extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    EmployeeListDAOImpl employeeDAO = new EmployeeListDAOImpl();
 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get employee ID from the request
        String id = request.getParameter("id");
        System.out.println(id);

        // Fetch employee data by ID
        Employee employee = employeeDAO.getEmployeeById(id);
        
        System.out.println("111");
        System.out.println(employee);

        if (employee != null) {
            request.setAttribute("employee", employee);
            // Forward to UpdateEmployee.jsp with the employee details
            request.getRequestDispatcher("EmployeeUpdate.jsp").include(request, response);
        } else {
            response.getWriter().println("Employee not found.");
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the form fields from the request
    	System.out.println("reached in second");
    	
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String gender = request.getParameter("gender");
        String[] coursesArray = request.getParameterValues("course");
        String courses = String.join(",", coursesArray);

        

        // Create the employee object
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setDesignation(designation);
        employee.setGender(gender);
        employee.setCourses(courses);

        // Handle the file upload for the image
        Part filePart = request.getPart("imgUpload");  // Retrieves the uploaded file part
        InputStream imgStream = null;

        if (filePart != null && filePart.getSize() > 0) {
            imgStream = filePart.getInputStream(); // Get input stream of the file
        }

        // Update the employee details in the database
        boolean isUpdated = employeeDAO.updateEmployee(employee, imgStream);
        System.out.println(isUpdated);
        
        System.out.println("reached in third");

        if (isUpdated) {
            response.sendRedirect("EmployeeList"); // Redirect to Employee List page after successful update
        } else {
            response.getWriter().println("Failed to update employee.");
        }
    }
}
