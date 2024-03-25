<!DOCTYPE html  >
<html >
<h:head>
    <title>Guide</title>
    <link rel="stylesheet" type="text/css" href="ressources/css/styles.css">
    <link rel="stylesheet" type="text/css" href="ressources/css/StyleSignUp.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script src="ressources/javascript/popup.js"></script>
    <script src="ressources/javascript/signupForm.js"></script>
</h:head>
<h:body>
     <div class="container">
     <div class="rectangle"></div>
    <img src="ressources/img/logo.jpg" alt="Logo" class="logo">
    <nav class="menu">
        <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="#">Contact</a></li>
        </ul>
    </nav>
</div>
<div class="authentification-menu"> 
	<div class = "carre"></div>
   <div class="authentication">
       <input type="text" placeholder="Email"> <br>
       <input type="password" placeholder="Password"> <br>
       <button >Login</button>
       <button id="create-account-btn">Cr�er un compte</button>
   </div>
 </div>
	<div ng-app="YourGuide" >
	<%@include file="signup.jsp" %>
	</div>
</h:body>
</html>
