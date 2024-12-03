<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Page</title>
    <link rel="stylesheet" href="Login.css">
    <style>
        /* Add custom style for the error message */
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-box">
            <h2>Login</h2>
            <form action="Login" method="post">
                <div class="input-group">
                    <input type="text" id="username" name="username" required>
                    <label for="username">Username</label>
                </div>
                <div class="input-group">
                    <input type="password" id="password" name="password" required>
                    <label for="password">Password</label>
                </div>
                <button type="submit" class="btn">Submit</button>
            </form>
            <div class="forgot-password">
                <a href="#">Forgot Password?</a>
            </div>

            <!-- Check if the "error" parameter is present -->
            <%
                String error = request.getParameter("error");
                if (error != null && error.equals("1")) {
            %>
                   <h5 style="color: red;">Incorrect Username/Password, Please Try Again</h5>
                
            <% 
                }
            %>
        </div>
    </div>
</body>
</html>
