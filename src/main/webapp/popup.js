// Écouteur d'événement pour le clic sur le bouton "Fermer" du popup
document.getElementById("close-popup-btn").addEventListener("click", closePopup);
document.addEventListener("DOMContentLoaded", function() {
    // Fonction pour ouvrir le popup
    function openPopup() {
        document.getElementById("popup").style.display = "block";
    }

    // Fonction pour fermer le popup
    function closePopup() {
        document.getElementById("popup").style.display = "none";
    }

    // Écouteur d'événement pour le clic sur le bouton "Créer un compte"
    var createAccountBtn = document.getElementById("create-account-btn");
    if (createAccountBtn) {
        createAccountBtn.addEventListener("click", openPopup);
    }

    // Écouteur d'événement pour le clic sur le bouton "Fermer" du popup
    var closePopupBtn = document.getElementById("close-popup-btn");
    if (closePopupBtn) {
        closePopupBtn.addEventListener("click", closePopup);
    }
});
