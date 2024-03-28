<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.JAVA.Beans.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: rgba(240, 240, 240, 0.5); /* Diminuer la transparence */
        }
        .container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            background-color: rgba(240, 240, 240, 0.5);
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            margin-bottom: 20px;
            text-align: center;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"], input[type="email"], select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            background-color: rgba(240, 240, 240, 0.5);
        }
        button {
            background-color: #;
            color: black;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 250px;
        }
        .profile-image {
            width: 150px; /* Ajustez la taille selon vos besoins */
            height: 150px; /* Ajustez la taille selon vos besoins */
            border-radius: 50%;
            margin: 0 auto 20px;
            display: block;
        }
        .navbar{
            height:70px;
            margin-bottom:50px;
        }
    </style>
    <title>Modifier le profil</title>
</head>
<body style="background: url('image/rabat.webp') center center / cover no-repeat fixed; height: 100vh;">
<nav class="navbar navbar-expand-lg navbar-light bg-light ">
    <a class="navbar-brand" href="Home.jsp"><img src="image/logo.png" alt="logo"></a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="Home.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Contact Us</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto" style="display: flex; align-items: center;">
    <li style="display: flex;">
        <form action="LogoutServlet" method="get" class="logout" style="margin-right: 5px;">
            <button type="submit" class="btn btn-secondary">Logout</button>
        </form>
        <button type="button" class="btn btn-secondary" onclick="window.location.href='Login.jsp'" style="margin-left: 5px;">Je suis un guide</button>
    </li>
</ul>
        
    </div> 
</nav>
<div class="container">
    <h1>Votre profil</h1>
    <div class="row">
        
        <div class="col-md-6">
            <form id="profile-form" action="ProfileServlet" method="post">
                <div class="form-group">
                    <label for="name">Nom:</label>
                    <input type="text" id="name" name="name" value="${user.name}" required>
                </div>
                
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" required>
                </div>
               
                <button type="submit">Modifier votre profil</button>
            </form>
        </div>
    </div>
</div>
<script >

function displayImage(input) {
    var file = input.files[0];
    if (file) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('profile-pic').src = e.target.result;
        };
        reader.readAsDataURL(file);
    }
}
</script>

    </body>
    </html>