<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.web.models.Employee" %>
<%@ page import="com.web.dao.EmployeeDAO" %>
<%@ page import="com.web.daoImp.EmployeeListDAOImpl" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="CreateEmployee.css" />
    <title>Update Employee</title>
    <script>
        function validateForm() {
            var email = document.getElementById('email').value;
            var fileInput = document.getElementById('imgUpload');
            var filePath = fileInput.value;
            var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
            if (!allowedExtensions.exec(filePath)) {
                alert('Please upload file having extensions .jpeg/.jpg/.png only.');
                fileInput.value = '';
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
                        <a href="EmployeeUpdate.jsp" id="employeeUpdateBtn">Employee Update</a>
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
            <h1 style="color: rgb(93, 97, 13);">Update Employee</h1>
            
            <!-- Fetch employee details from the request -->
            <%
                String id = request.getParameter("id");
                EmployeeListDAOImpl employeeDAO = new EmployeeListDAOImpl();
                Employee employee = employeeDAO.getEmployeeById(id);
                
                System.out.println("222");
                System.out.println(employee.getName());
            %>

            <form id="employeeForm" action="UpdateEmployee?id=<%= employee.getId() %>" onsubmit="return validateForm()" method="POST" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><label for="name">Name:</label></td>
                        <td><input type="text" id="name" name="name" value="<%= employee.getName() %>" required></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email:</label></td>
                        <td><input type="email" id="email" name="email" value="<%= employee.getEmail() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="mobile">Mobile No:</label></td>
                        <td><input type="text" id="mobile" name="mobile" value="<%= employee.getMobile() %>" readonly></td>
                    </tr>
                    <tr>
                        <td><label for="designation">Designation:</label></td>
                        <td>
                            <select id="designation" name="designation" required>
                                <option value="HR" <%= "HR".equals(employee.getDesignation()) ? "selected" : "" %>>HR</option>
                                <option value="Manager" <%= "Manager".equals(employee.getDesignation()) ? "selected" : "" %>>Manager</option>
                                <option value="Sales" <%= "Sales".equals(employee.getDesignation()) ? "selected" : "" %>>Sales</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Gender:</label></td>
                        <td>
                            <input type="radio" id="male" name="gender" value="M" <%= "M".equals(employee.getGender()) ? "checked" : "" %>> Male
                            <input type="radio" id="female" name="gender" value="F" <%= "F".equals(employee.getGender()) ? "checked" : "" %>> Female
                        </td>
                    </tr>
                    <tr>
                        <td><label>Course:</label></td>
                        <td>
                            <input type="checkbox" id="mca" name="course" value="MCA" <%= employee.getCourses().contains("MCA") ? "checked" : "" %>> MCA
                            <input type="checkbox" id="bca" name="course" value="BCA" <%= employee.getCourses().contains("BCA") ? "checked" : "" %>> BCA
                            <input type="checkbox" id="bsc" name="course" value="BSC" <%= employee.getCourses().contains("BSC") ? "checked" : "" %>> BSC
                        </td>
                    </tr>
                    <tr>
                        <td><label for="imgUpload">Img Upload:</label></td>
                        <td><input type="file" id="imgUpload" name="imgUpload" accept=".jpg,.jpeg,.png"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button type="submit" class="btn">Update</button></td>
                    </tr>
                </table>
                
                <% 
                    String error = request.getParameter("error");
                    String success = request.getParameter("success");
                    if (error != null && error.equals("updateFailed")) {
                %>
                    <h5 style="color: red;">Failed to Update Employee!</h5>
                <% 
                    } else if (success != null && success.equals("updateSuccess")) {
                %>
                    <h4 style="color: green;">Employee Updated Successfully!</h4>
                <% 
                    } 
                %>
                
            </form>
        </div>
    </div>

    <script src="welcome.js"></script>
</body>
</html>
