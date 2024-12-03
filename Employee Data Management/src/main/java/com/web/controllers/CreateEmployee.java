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

import com.web.daoImp.CreateEmployeeDaoImpl;
import com.web.models.Employee;

@WebServlet("/CreateEmployee")
@MultipartConfig(
	    maxFileSize = 1024 * 1024 * 10,  // Maximum file size (100MB)
	    maxRequestSize = 1024 * 1024 * 10 // Maximum request size (120MB, in case you have other fields)
	)
public class CreateEmployee extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String designation = req.getParameter("designation");
        String gender = req.getParameter("gender");

        // Reading multiple checkboxes for courses
        String[] coursesArray = req.getParameterValues("course");
        String courses = String.join(",", coursesArray);

        // Handle file upload
        Part filePart = req.getPart("imgUpload");
        byte[] imageData = null;

        if (filePart != null) {
            if (filePart.getSize() > 0 && filePart.getSize() <= 1024 * 1024 * 10) { // Limit to 10MB
                imageData = filePart.getInputStream().readAllBytes();
            } else {
                resp.sendRedirect("CreateEmployee.jsp?error=imageTooLarge");
                return;
            }
        }

        // Create Employee object
        Employee employee = new Employee(name, email, mobile, designation, gender, courses, imageData);
        CreateEmployeeDaoImpl ce = new CreateEmployeeDaoImpl();

        // Check for duplicate email
        if (ce.isEmailDuplicate(email)) {
            resp.sendRedirect("CreateEmployee.jsp?error=emailExists");
        } else {
            boolean isUpload = ce.insertEmployeeData(employee);
            if (isUpload) {
                resp.sendRedirect("CreateEmployee.jsp?success=1");
            } else {
                resp.sendRedirect("CreateEmployee.jsp?error=1");
            }
        }
    }
}

