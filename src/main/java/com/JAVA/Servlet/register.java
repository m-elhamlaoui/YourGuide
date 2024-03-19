package com.JAVA.Servlet;

import java.io.IOException;
import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.UserDAO;
import com.JAVA.DAO.VilleDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class register extends HttpServlet {
    // ... (imports et autres annotations de servlet)

    /**
	 * 
	 */
	
	

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
    	String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String sexe = request.getParameter("sexe");
		String age = request.getParameter("age");
		String ville = request.getParameter("ville");
		String tel = request.getParameter("tel");


        try {
            if (password.equals(confirmPassword)) {
                // Créer un objet User
                User user = new User();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setSexe(sexe);
                user.setAge(age);
                user.setVille(ville);
                user.setTel(tel);
                // Utiliser le DAO pour ajouter l'utilisateur à la base de données
                UserDAO userDao = new UserDAO(); 
                userDao.insertUser(user);
                
                VilleDAO villedao = new VilleDAO();
                villedao.addVille(ville);
                

                // Rediriger vers la page login.jsp après un enregistrement réussi
                response.sendRedirect("Login.jsp");
            } else {
                // Les mots de passe ne correspondent pas, gérer cela en conséquence
                request.setAttribute("error", "Les mots de passe ne correspondent pas");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
                dispatcher.forward(request, response);
            }
        } catch (DAOException e) {
            // Gérer les erreurs liées à la base de données
            e.printStackTrace(); // À remplacer par une gestion appropriée des erreurs
            request.setAttribute("error", "Erreur liée à la base de données : " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            // Gérer d'autres exceptions si nécessaire
            e.printStackTrace(); // Loguer l'exception
            request.setAttribute("error", "Une erreur inattendue s'est produite : " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
    }
}



