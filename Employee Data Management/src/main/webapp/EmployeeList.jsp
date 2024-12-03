<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.web.models.Employee" %>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="CreateEmployee.css" />
    <title>Employee List</title>
    <script>
        function validateSearchForm() {
            var searchInput = document.getElementById('searchInput').value;
            if (searchInput.length < 3 && searchInput.length > 0) {
                alert('Search keyword should be at least 3 characters long.');
                return false;
            }
            return true;
        }
    </script>
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
                        <a href="UpdateEmployee.jsp" id="employeeUpdateBtn">Employee Update</a>
                    </div>
                </li>
                <% 
                    HttpSession session2 = request.getSession(false); 
                    String username = (session2 != null) ? (String) session2.getAttribute("username") : null;
                    if (username != null) {
                %>
                    <li><a href="Login.jsp"><%= username %> - Log Out</a></li>
                <% } else { %>
                    <li><a href="Login.jsp">Login</a></li>
                <% } %>
            </ul>
        </div>
    </nav>   

    <!-- Dynamic Content Section -->
    <div id="dynamicContent" class="container">
        <div class="hero">
            <h1 style="color: rgb(93, 97, 13);">Employee List</h1>
            
            <!-- Total Count and Create Employee -->
            <div style="margin-bottom: 20px;">
                <span>Total Count: <%= request.getAttribute("totalCount") %></span>
                <a href="CreateEmployee.jsp" class="btn" style="float: right;">Create Employee</a>
            </div>

            <!-- Search Form -->
            <form id="searchForm" action="EmployeeList" onsubmit="return validateSearchForm()" method="GET">
                <label for="searchInput">Search:</label>
                <input type="text" id="searchInput" name="search" placeholder="Enter Search Keyword">
                <button type="submit" class="btn">Search</button>
            </form>

            <!-- Employee Table -->
            <table border="1" cellpadding="5" cellspacing="0" style="width: 100%; margin-top: 20px;">
                <thead>
                    <tr>
                        <th>Unique Id</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Mobile No</th>
                        <th>Designation</th>
                        <th>Gender</th>
                        <th>Course</th>
                        <th>Create Date</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Employee> employees = (List<Employee>) request.getAttribute("employeeList");
                        if (employees == null) {
                            employees = new ArrayList<>(); // Initialize to an empty list if null
                        }

                        if (employees.isEmpty()) {
                    %>
                    <tr>
                        <td colspan="10">No records found.</td>
                    </tr>
                    <% 
                        } else {
                            for (Employee employee : employees) {
                    %>
                    <tr>
                        <td><%= employee.getId() %></td>
                        <td><img src="<%= employee.getImageUrl() %>" alt="Image" style="width: 50px; height: 50px;" loading="lazy"></td>
                        
                        <td><%= employee.getName() %></td>
                        <td><%= employee.getEmail() %></td>
                        <td><%= employee.getMobile() %></td>
                        <td><%= employee.getDesignation() %></td>
                        <td><%= employee.getGender() %></td>
                        <td><%= employee.getCourses() %></td>
                        <td><%= employee.getCreatedDate() %></td>
                        <td>
                            <a href="EmployeeUpdate.jsp?id=<%= employee.getId() %>">Edit</a> - 
                            <a href="DeleteEmployee?id=<%= employee.getId() %>" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                        </td>
                    </tr>
                    <% 
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <script src="welcome.js"></script>
</body>
</html>
