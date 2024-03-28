<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<h:head>
    <meta charset="UTF-8">
    <title>Sign Up Form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.css">
    
    <!-- Include AngularJS and Angular Material -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.9/angular-aria.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-material/1.1.24/angular-material.min.js"></script>
    <style>
        /* CSS for body background */
        body {
    position: relative; /* Required for positioning the pseudo-element */
    background-image: url('ressources/img/img2.jpg'); /* Replace 'path/to/background-image.jpg' with your image path */
    background-size: cover;
    background-repeat: no-repeat;
    margin: 0;
    padding: 0;
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
            margin-left: 20px;
        }

        .navigation-links ul li a {
            text-decoration: none;
            color: #333;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .navigation-links ul li a:hover {
            color: #63492B; /* Change color on hover */
        }

        .search-bar input[type="text"] {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            transition: border-color 0.3s ease;
        }

        .search-bar input[type="text"]:focus {
            outline: none;
            border-color: #63492B; /* Change border color on focus */
        }
       
       #SignupContent {
            border-radius: 10px; /* Add border radius for styling */
            color: black;
        }
        
       body::after {
    content: "";
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(235, 229, 224, 0.4); /* Fond transparent */
    z-index: -1; /* Assure que l'élément pseudo est derrière le contenu */
}  
       .box{
       background-color: rgba(240, 240, 240, 0.5); /* Semi-transparent background color */
           padding: 20px; /* Padding around the content inside the box */
    border-radius: 10px; /* Rounded corners for the box */
    max-width: 100%;
    max-height:100%; /* Maximum width of the box */
    margin-top: 70px; /* Center the box horizontally */
    margin-bottom:20px;
    margin-left:250px;
    max-width: 1000px; /* Maximum width of the box */
            width: 100%;
} 
container {
            background-color: rgba(240, 240, 240, 0.5); /* Semi-transparent background color */
            padding: 20px; /* Padding around the content inside the box */
            border-radius: 10px; /* Rounded corners for the box */
            max-width: 550px; /* Maximum width of the box */
            width: 100%;
            max-height:500px;
            height:100%;
            text-align: center; /* Center the content inside the box */
                        margin-left:500px;
            
        }
        

        /* CSS for login form */
        .login-form {
            background-color: rgba(240, 240, 240, 0.8); /* Semi-transparent background color */        
            max-width: 400px; /* Maximum width of the box */
            width: 100%;
            max-height:500px;
            height:100%;
            margin: auto; /* Center the form horizontally */
        }
        .btn{
        display:flex;
        margin-left:80px;
        margin-bottom:20px;
        }

    </style>
</h:head>
<h:body ng-app="MyApp" ng-controller="DemoCtrl" ng-cloak >
  
<div class="header">
    <img src="ressources/img/logo.jpg" alt="Logo" class="logo">
    <div class="navigation-links">
        <ul>
            <li><a href="Home.jsp">Home</a></li>
            <li><a href="#">Contact</a></li>
            <li class="search-bar"><input type="text" placeholder="Search"></li>
            <li><a href="login">Je suis guide</a></li>
        </ul>
    </div>
</div>
<div class="box">
<div style="display: flex; justify-content: center; align-items: center; ">
    <p style="font-size: 55px; font-family:bold;">Bienvenu sur <span style="color:#1E3718">YOURGUIDE</span></p>
</div>

          <div>
          <div class="container">
    <form class="login-form" action="LoginServlet" method="post">
        <input type="hidden" name="action" value="signup" />
        <div layout-padding>
            <md-input-container>
                <label for="email">Email:</label>
                <input type="text" id="email" name="email" placeholder="Enter your email" required>
            </md-input-container>
            <md-input-container>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </md-input-container>
        </div>
        <div class="btn">
            <button type="submit" class="btn btn-primary btn-lg">Log In</button>
        </div>
    </form>
    </div>
          </div>
          <div>
          <a href="registration.jsp" style="color: black;">
              <p>Don't have an account yet? Sign Up</p>
          </a>
          </div>
         
          

</div>
<script>
    angular.module('MyApp', ['ngMaterial', 'ngMessages']).controller('DemoCtrl', function ($scope, $http) {
        
      
       
    });
</script>

</h:body>
</html>


