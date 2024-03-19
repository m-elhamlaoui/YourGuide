<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- Affichage du message d'erreur s'il est présent --%>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
   <div class="login-container">
        <h2>Login</h2>
        <form class="login-form" action="LoginServlet" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" placeholder="Enter your email" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            <div class="d-grid">
                <button type="submit" class="btn btn-primary btn-lg">Log In</button>
            </div>
        </form>
    </div>
</body>
</html>