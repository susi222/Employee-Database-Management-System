<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="Welcome.css" />
    <title>Admin Dashboard</title>
</head>
<body style="color: #c2780a;">
    <nav class="nav">
        <div class="container">
            <h1 class="logo"><a href="Ambati Vishnu-.pdf">Admin</a></h1>
            <ul>
                <li><a href="Welcome.html" id="dashboardBtn" class="current">Home</a></li>
                <li class="dropdown">
                    <a href="#" class="dropbtn">Employee List &#9660;</a>
                    <div class="dropdown-content">
                        <a href="CreateEmployee.jsp" id="createEmployeeBtn">Employee Create</a>
                        <a href="EmployeeList.jsp" id="employeeListBtn">Employee List</a>
                        <a href="EmployeeUpdate.jsp" id="employeeUpdateBtn">Employee Update</a>
                    </div>
                </li>
                
                
		<% 
            HttpSession session1 = request.getSession(false); 
            String username = (session1 != null) ? (String) session1.getAttribute("username") : null;
            
            if (username != null) {
        %>
            <div>
                
                <li><a href="Login.jsp"><%= username %> - Log Out</a></li>
            </div>
        <% 
            } else { 
        %>
            <li><a href="Login.jsp">Login</a></li>
        <% 
            } 
        %>

            </ul>
        </div>
    </nav>

    <!-- Dynamic Content Section -->
    <div id="dynamicContent" class="container">
        <div class="hero">
            <h1 style="color: rgb(93, 97, 13);">Welcome to the Admin Panel</h1>
        </div>
    </div>

    <script src="welcome.js"></script>
</body>
</html>
