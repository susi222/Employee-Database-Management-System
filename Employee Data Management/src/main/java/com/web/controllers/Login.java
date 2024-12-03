package com.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.web.daoImp.AdminDaoImpl;
import com.web.models.Admin;

@SuppressWarnings("serial")
@WebServlet("/Login")
public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Initialize AdminDaoImpl to validate the user credentials
        AdminDaoImpl adminDao = new AdminDaoImpl();
        Admin admin = new Admin(username, password);

        // Validate the admin credentials
        boolean isValid = adminDao.validateAdmin(admin);
       // System.out.println(isValid);
        
        if (isValid) {
            // If the login is successful, create a session
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            
            Cookie c1=new Cookie("username", username);
            c1.setMaxAge(10000);
            resp.addCookie(c1);
            
            resp.sendRedirect("Welcome.jsp");
        } else {
            resp.sendRedirect("Login.jsp?error=1");
        }
    }
}
