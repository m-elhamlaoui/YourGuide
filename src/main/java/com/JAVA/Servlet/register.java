package com.JAVA.Servlet;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.JAVA.Beans.User;
import com.JAVA.DAO.DAOException;
import com.JAVA.DAO.UserDAO;
import com.JAVA.DAO.VilleDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@MultipartConfig
public class register extends HttpServlet {
    // ... (imports et autres annotations de servlet)

    /**
	 * 
	 */
	
	

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Récupérer les autres paramètres du formulaire
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmPassword");
	    String sexe = request.getParameter("sexe");
	    String age = request.getParameter("age");
	    String ville = request.getParameter("ville");
	    String tel = request.getParameter("tel");
	    String langues = request.getParameter("langues");
	    Part filePart = request.getPart("image");
	    InputStream inputStream = filePart.getInputStream();
	    byte[] imageData = null;
	    if (inputStream != null) {
	        // Convertir l'image en tableau de bytes
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            baos.write(buffer, 0, bytesRead);
	        }
	        imageData = baos.toByteArray();
	        inputStream.close();
	        baos.close();
	    }

	    try {
	        if (password.equals(confirmPassword)) {
	            // Créer un objet User avec les données récupérées
	            User user = new User();
	            user.setName(name);
	            user.setEmail(email);
	            user.setPassword(password);
	            user.setSexe(sexe);
	            user.setAge(age);
	            user.setVille(ville);
	            user.setTel(tel);
	            user.setPicture(imageData); // Ajouter l'image
	            user.setLangue(langues); // Ajouter les langues sélectionnées

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



