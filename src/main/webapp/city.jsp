
<%@ page import="java.util.List" %>
<%@ page import="model.Guide" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Guides</title>
    <style>
        /* Add your CSS styles here */
        /* This is just a simple example, customize as needed */
        .city-name {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .city-description {
            text-align: center;
            font-style: italic;
        }
        .guide-card {
            border: 1px solid #ccc;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent background */
        }
        .guide-photo {
            border-radius: 50%;
            width: 100px;
            height: 100px;
            background-color: #efefef; /* Light gray background */
            display: inline-block;
            margin-right: 20px;
            overflow: hidden;
        }
        .guide-photo img {
            width: 100%;
            height: auto;
        }
        .guide-info {
            display: inline-block;
            vertical-align: top;
        }
    </style>
</head>
<body>

<h2 class="city-name">Name of the City</h2>
<p class="city-description">Découvrez la beauté de la ville avec des guides expérimentés et bienveillants !</p>

<h2>List of Guides</h2>

<%
    // Assuming you have a method to fetch guides from the database
    List<Guide> guides = GuideDAO.getAllGuides(); // Change GuideDAO to your DAO class
    
    for (Guide guide : guides) { 
%>
<div class="guide-card">
    <div class="guide-photo">
        <!-- Displaying guide's photo -->
        <img src="data:image/jpeg;base64, <%= guide.getPhotoBase64() %>" alt="Guide Photo">
    </div>
    <div class="guide-info">
        <h3><%= guide.getFirstName() %> <%= guide.getLastName() %></h3>
        <p>Other information about the guide...</p>
    </div>
</div>
<% } %>

</body>
</html>
