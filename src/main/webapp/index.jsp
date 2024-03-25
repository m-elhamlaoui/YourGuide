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
    </style>
</h:head>
<h:body>
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
    <div style="background-color: rgba(235, 229, 224, 0.8); padding: 20px; text-align: center; margin: 15%;border-radius: 3px;">
        <p style="font-style: italic; color: #63492B;">Explorez le Maroc avec un guide dedie a vos cotes</p>
        <p style="color: #000000;">Accordez-vous le luxe d'un voyage sur mesure avec des guides locaux passionnes, prets a partager leurs connaissances et a vous immerger dans la culture de chaque destination. Ensemble, creons des souvenirs qui transcendent les frontieres et faconnent des experiences uniques. Bienvenue dans un monde de voyages personnalises, ou chaque instant devient une histoire a raconter.</p>
    </div>
</h:body>
</html>
