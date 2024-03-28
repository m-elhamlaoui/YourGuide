<!DOCTYPE html>
<html>
<head>
    <!-- Google Fonts ================================================== -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.google.com" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <meta charset="UTF-8">
    <title>Guides</title>
</head>
<body style="background: url('image/rabat.webp') center center / cover no-repeat fixed; height: 100vh;">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
        <ul class="navbar-nav ml-auto">
            <li class="btn">
                <button type="button" class="btn btn-secondary" onclick="window.location.href='ViewProfile.jsp'">Je suis un guide</button>
            </li>
   
        </ul>
        
    </div> 
</nav>

<section id="guide">
    <div class="container">
        <h1 style="font-family: 'Island Moments', sans-serif;">
            <% String cityName = request.getParameter("ville");
            if (cityName != null) {
                out.println(cityName);
            } %>
        </h1>
        <h3>
            <% if (cityName != null) {
                out.println("Découvrez la beauté de " + cityName + " avec des guides expérimentés et bienveillants!");
            } %>
        </h3>
        <div class="content-box">
            <p>Imprégnez-vous de l'atmosphère envoûtante des ruelles pittoresques, <br>contemplez l'architecture fascinante des monuments emblématiques<br> et laissez-vous charmer par la diversité de ses traditions.</p>
        </div>
        <!-- Guide profiles will be displayed here -->
        <div id="guideProfiles" class="profiles-container"></div>
    </div>
</section>

<div class="popup-container" id="popupContainer">
    <div class="popup-content" id="popupContent">
        <button class="close-btn" onclick="closePopup()">Fermer</button> <!-- Bouton de fermeture -->
        <textarea id="comment" rows="4" cols="50" placeholder="Ajouter un commentaire..."></textarea> <!-- Zone de texte pour le commentaire -->
        <button onclick="submitComment()">Envoyer</button> <!-- Bouton pour envoyer le commentaire -->
    </div>
</div>

<script>
    // Function to fetch guide profiles using AJAX
    function fetchGuideProfiles(city) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("guideProfiles").innerHTML = this.responseText;
            }
        };
        xhttp.open("GET", "GuideServlet?ville=" + city, true);
        xhttp.send();
    }

    // Call the function to fetch guide profiles when the page loads
    window.onload = function() {
        var cityName = "<%= request.getParameter("ville") %>";
        if (cityName) {
            fetchGuideProfiles(cityName);
        }
    };

    // Function to display guide information in a pop-up
   // Function to display guide information in a pop-up
function showGuideInfo(name, email, sexe, tel, langue,tarif) {
    var modalContent = "<p>Nom: " + name + "</p>" +
        "<p>Email: " + email + "</p>" +
        "<p>Sexe: " + sexe + "</p>" +
        "<p>Tel: " + tel + "</p>" +
        "<p> Langue:" + langue +"</p>"+
        "<p> Tarif :"+ tarif +"</p>"+
        "<textarea id='comment' rows='4' cols='50' placeholder='Ajouter un commentaire...'></textarea>" +
        "<button class='submit-btn' onclick='submitComment()'>Envoyer</button>"+
        "<button class='close-btn' onclick='closePopup()''>Fermer</button>";

    // Display the popup
    var popupContainer = document.getElementById('popupContainer');
    var popupContent = document.getElementById('popupContent');
    popupContent.innerHTML = modalContent;
    popupContainer.style.display = 'flex';
}


    function closePopup() {
        var popupContainer = document.getElementById("popupContainer");
        popupContainer.style.display = "none";
    }

    function submitComment() {
        var comment = document.getElementById('comment').value; // Récupérer la valeur du commentaire
        // Faire quelque chose avec le commentaire, par exemple l'envoyer à un serveur ou l'afficher dans la console
        console.log('Commentaire ajouté : ' + comment);
    }
</script>

</body>
</html>
