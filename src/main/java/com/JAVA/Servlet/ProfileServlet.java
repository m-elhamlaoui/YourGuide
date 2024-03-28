package com.JAVA.Servlet;

import java.io.IOException;
import java.util.List;

import com.JAVA.Beans.User;
import com.JAVA.DAO.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'utilisateur de la session
        User user = (User) request.getSession().getAttribute("authenticatedUser");

        if (user != null) {
            // Utilisateur authentifié trouvé dans la session
            System.out.println("Utilisateur authentifié récupéré de la session : " + user.getName());
            
            // Récupérer les informations du guide depuis la base de données
            UserDAO userDAO = new UserDAO();
            List<User> guides = userDAO.getGuidesByCity(user.getVille());
            
            // Définir l'attribut user et guides dans la requête pour les afficher dans la JSP
            request.setAttribute("user", user);
            request.setAttribute("guides", guides);
            
            // Rediriger vers la page de profil
            request.getRequestDispatcher("ViewProfile.jsp").forward(request, response);
        } else {
            // Aucun utilisateur authentifié trouvé dans la session, rediriger vers la page de connexion
            System.out.println("Aucun utilisateur authentifié trouvé dans la session. Redirection vers la page de connexion.");
            response.sendRedirect("Login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
