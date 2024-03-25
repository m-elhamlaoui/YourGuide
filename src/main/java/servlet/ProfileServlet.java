package servlet;

import java.io.IOException;

import model.Guide;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur de la session
        Guide guide = (Guide) request.getSession().getAttribute("authenticatedUser");

        if (guide != null) {
            // Utilisateur authentifié trouvé dans la session
            System.out.println("Utilisateur authentifié récupéré de la session : " + guide.getFirstName() + ' ' + guide.getLastName());
            // Définir l'attribut user dans la requête pour l'afficher dans la JSP
            request.setAttribute("user", guide);
            // Rediriger vers la page de profil
            request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);
        } else {
            // Aucun utilisateur authentifié trouvé dans la session, rediriger vers la page de connexion
            System.out.println("Aucun utilisateur authentifié trouvé dans la session. Redirection vers la page de connexion.");
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
