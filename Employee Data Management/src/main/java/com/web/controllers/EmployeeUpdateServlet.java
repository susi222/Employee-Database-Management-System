package com.web.controllers;

import com.web.dao.EmployeeDAO;
import com.web.models.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("serial")
@WebServlet("")
public class EmployeeUpdateServlet extends HttpServlet {

    private EmployeeDAO employeeDAO;


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String designation = request.getParameter("designation");
        String gender = request.getParameter("gender");
        String[] coursesArray = request.getParameterValues("course");
        String courses = (coursesArray != null) ? String.join(",", coursesArray) : "";
        
        Part filePart = request.getPart("imgUpload");
        InputStream imgStream = (filePart != null) ? filePart.getInputStream() : null;

        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setMobile(mobile);
        employee.setDesignation(designation);
        employee.setGender(gender);
        employee.setCourses(courses);
        employee.setImageData(imgStream != null ? imgStream.readAllBytes() : null);

        boolean isUpdated = employeeDAO.updateEmployee(employee, imgStream);
        
        if (isUpdated) {
            response.sendRedirect("EmployeeList?success=updateSuccess");
        } else {
            request.setAttribute("error", "updateFailed");
            request.getRequestDispatcher("EmployeeUpdate.jsp?id=" + id).forward(request, response);
        }
    }
}
