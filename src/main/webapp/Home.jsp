<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.JAVA.Beans.User" %>
<%@ page import="com.JAVA.DAO.UserDAO" %>
<%@ page import="com.JAVA.DAO.VilleDAO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Votre titre ici</title>
<script>
function getGuidesByCity() {
    var selectedCity = document.getElementById("cities").value;
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "GuideServlet?ville=" + selectedCity, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            document.getElementById("guidesList").innerHTML = xhr.responseText;
        }
    };
    xhr.send();
}


</script>
</head>
<body>
<nav>
    <a href="#home">Home</a>
    <a href="#about">About</a>
    <a href="#contact">Contact</a>
    <!-- Ajoutez d'autres liens de navigation si nécessaire -->
    
    <!-- Bouton de déconnexion -->
    <form action="LogoutServlet" method="get" class="logout">
        <button type="submit">Logout</button>
    </form>
</nav>
<div>
<h2>YourGuide dans votre voyage</h2>
<div>
<h1>Ville</h1>
<select id="cities">
<option>Choisissez une ville</option>
    <% 
        VilleDAO villeDAO = new VilleDAO();
        List<String> villes = villeDAO.getAllVilles();
        for (String ville : villes) { 
    %>
        <option><%= ville %></option>
    <% } %>
</select>
</div>
<div>
<button onclick="getGuidesByCity()">Afficher Guides</button>

</div>
<div id="guidesList"></div>


</div>
</body>
</html>
