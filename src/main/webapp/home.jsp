<%@ page import="java.util.List" %>
<%@ page import="model.City" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
    xmlns:h="http://xmlns.jcp.org/jsf/html"
    xmlns:f="http://xmlns.jcp.org/jsf/core"
    xmlns:ui="http://xmlns.jcp.org/jsf/facelets">
<h:head>
    <title>Sign Up Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.css">
    <style>
        /* CSS for body background */
        body {
            background-image: url('ressources/img/img2.jpg'); /* Replace 'path/to/background-image.jpg' with your image path */
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            margin: 0; /* Remove default body margin */
            padding: 0; /* Remove default body padding */
        }

        /* CSS for header layout */
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 20px;
            background-color: rgba(235, 229, 224, 0.8); /* Semi-transparent background for better readability */
        }

        .logo {
            height: 40px; /* Adjust height as needed */
        }

        .navigation-links ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
        }

        .navigation-links ul li {
            margin-left: 10px;
        }

        .search-bar input[type="text"] {
            padding: 5px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        /* CSS for displaying cities as cards */
        .cities-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            padding: 20px;
        }

        

        .city-card:hover {
            transform: scale(1.05); /* Increase card size on hover */
        }
        /* Style for anchor tag */
    .city-link {
        text-decoration: none; /* Remove underline */
        color: inherit; /* Inherit text color from parent */
    }

    /* Style for city card */
    .city-card {
        width: 200px;
        height: 100px;
        margin: 40px;
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        background-color: #7E7E7E;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        cursor: pointer;
        transition: transform 0.3s ease-in-out;
        display: flex; /* Use flexbox for centering */
        justify-content: center; /* Center horizontally */
        align-items: center; /* Center vertically */
        

    }

    /* Style for city name */
    .city-name {
        text-align: center; /* Center text */
    }
    </style>
</h:head>
<h:body ng-app="MyApp" ng-controller="DemoCtrl" ng-cloak>
   <div class="header">
       <img src="ressources/img/logo.jpg" alt="Logo" class="logo">
       <div class="navigation-links">
           <ul>
               <li><a href="index.jsp">Home</a></li>
               <li><a href="#">Contact</a></li>
               <li class="search-bar"><input type="text" placeholder="Search"></li>
               <li><a href="login">Je suis guide</a></li>
           </ul>
       </div>
   </div>

   <div class="cities-container">
    <!-- Loop through cities obtained from the servlet -->
    <% 
        List<City> cities = (List<City>) request.getAttribute("cities");
        if (cities != null) {
            for (City city : cities) { 
    %>
            <a href="cityPage?selectedCity=<%= city.getCityName() %>" class="city-link">
                <div class="city-card">
                <div class="name"><%= city.getCityName() %></div>
            </div>
            </a>
    <% 
            } 
        } else {
    %>
            <div>No cities found</div>
    <% } %>
</div>

   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-messages.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-animate.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-aria.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.js"></script>
   <script>
       angular.module('MyApp', ['ngMaterial', 'ngMessages']).controller('DemoCtrl', function ($scope, $http) {
           
         
       });
   </script>
</h:body>
</html>
