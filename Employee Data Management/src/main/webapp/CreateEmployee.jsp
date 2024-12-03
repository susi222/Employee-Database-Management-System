<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="CreateEmployee.css" />
    <title>Create Employee</title>
    <script>
        function validateForm() {
            var email = document.getElementById('email').value;
            var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            if (!emailPattern.test(email)) {
                alert('Invalid Email Address');
                return false;
            }

            // Image validation
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
            <h1 style="color: rgb(93, 97, 13);">Create Employee</h1>
            <form id="employeeForm" action="CreateEmployee" onsubmit="return validateForm()" method="POST" enctype="multipart/form-data">
                <table>
                    <tr>
                        <td><label for="name">Name:</label></td>
                        <td><input type="text" id="name" name="name" required></td>
                    </tr>
                    <tr>
                        <td><label for="email">Email:</label></td>
                        <td><input type="email" id="email" name="email" required></td>
                    </tr>
                    <tr>
                        <td><label for="mobile">Mobile No:</label></td>
                        <td><input type="text" id="mobile" name="mobile" pattern="\d*" maxlength="10" required></td>
                    </tr>
                    <tr>
                        <td><label for="designation">Designation:</label></td>
                        <td>
                            <select id="designation" name="designation" required>
                                <option value="HR">HR</option>
                                <option value="Manager">Manager</option>
                                <option value="Sales">Sales</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Gender:</label></td>
                        <td>
                            <input type="radio" id="male" name="gender" value="M" required> Male
                            <input type="radio" id="female" name="gender" value="F"> Female
                        </td>
                    </tr>
                    <tr>
                        <td><label>Course:</label></td>
                        <td>
                            <input type="checkbox" id="mca" name="course" value="MCA"> MCA
                            <input type="checkbox" id="bca" name="course" value="BCA"> BCA
                            <input type="checkbox" id="bsc" name="course" value="BSC"> BSC
                        </td>
                    </tr>
                    <tr>
                        <td><label for="imgUpload">Img Upload:</label></td>
                        <td><input type="file" id="imgUpload" name="imgUpload" accept=".jpg,.jpeg,.png"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><button type="submit" class="btn">Submit</button></td>
                    </tr>
                </table>
                
                 <%
                    String error = request.getParameter("error");
                    String success = request.getParameter("success");
                    if (error != null && error.equals("emailExists")) {
                 %>
                   <h5 style="color: yellow;">Email Already Exists!</h5>
                   <%
                    }
                    else if (error != null && error.equals("1")) {
                  %>
                  <h5 style="color: red;">Failed to Create...!</h5>
                  <%
                    }
                    else if (success != null && success.equals("1")) {
                  %>
                  <h4 style="color: green;">Uploaded Successfully...!</h4>
                  <%
                    }
                  %>                
            </form>
        </div>
    </div>

    <script src="welcome.js"></script>
</body>
</html>
