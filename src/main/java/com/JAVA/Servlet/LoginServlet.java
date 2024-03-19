package com.JAVA.Servlet;

import java.io.IOException;

import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.UserDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


	public class LoginServlet extends HttpServlet {
	    // ... (imports et autres annotations)

	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String email = request.getParameter("email");
	        String password = request.getParameter("password");

	        UserDAO userDao = new UserDAO();

	        try {
	            User user = userDao.getUserByEmail(email);

	            if (user != null && user.getPassword().equals(password)) {
	                // Authentification réussie, créer une session
	                HttpSession session = request.getSession();
	                
	                // Stocker l'utilisateur dans la session
	                session.setAttribute("user", user);

	                // Rediriger vers la page d'accueil
	                response.sendRedirect("Home.jsp");
	            } else {
	                // Échec de l'authentification, rediriger vers la page de connexion avec un message d'erreur
	                request.setAttribute("error", "Adresse e-mail ou mot de passe incorrect");
	                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
	                dispatcher.forward(request, response);
	            }
	        } catch (DAOException e) {
	            // Gérer les exceptions liées à la base de données
	            e.printStackTrace(); // À remplacer par une gestion appropriée des erreurs
	        }
	    }
	}




