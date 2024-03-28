package com.JAVA.Servlet;

import java.io.IOException;
import java.util.List;

import com.JAVA.Beans.User;
import com.JAVA.DAO.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class GuideServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ville = request.getParameter("ville");
       
        // Vous devez définir l'objet user ici, soit en le récupérant à partir de la session, soit en le créant
        
        // Ajoutez l'utilisateur à l'attribut de session
        // Retrieve guides based on the type of request
        List<User> guides;
        UserDAO userDAO = new UserDAO();
        if (ville != null) {
            guides = userDAO.getGuidesByCity(ville);
        
        } else {
            // Handle invalid request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        

        // Prepare HTML content
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html>");
        htmlResponse.append("<html>");
        htmlResponse.append("<head>");
        htmlResponse.append("<meta charset='UTF-8'>");
        htmlResponse.append("<title>Guides</title>");
        htmlResponse.append("</head>");
        htmlResponse.append("<body>");

     // Loop through guides and generate profile cards
        htmlResponse.append("<div class='profile-container'>"); // Add a container for the profiles
        for (User guide : guides) {
            htmlResponse.append("<div class='profile-card'>");
            htmlResponse.append("<img src='data:image/jpeg;base64," + encodeImage(guide.getPicture()) + "' alt='" + guide.getName() + "' class='profile-picture'>");
            htmlResponse.append("<p class='profile-name'><strong><a onclick='showGuideInfo(\"" + guide.getName() + "\", \"" + guide.getEmail() + "\", \"" + guide.getSexe() + "\", \"" + guide.getTel() + "\", \"" + guide.getLangue() +  "\", \"" + guide.getTarif() + "\")'>" + guide.getName() +"</strong></p></a>");
            htmlResponse.append("</div>");
        }
        htmlResponse.append("</div>"); // Close the profile container

        htmlResponse.append("</body>");
        htmlResponse.append("</html>");

        // Set response content type
        response.setContentType("text/html");

        // Write HTML content to response
        response.getWriter().println(htmlResponse.toString());
     // Récupérer l'utilisateur depuis la base de données ou d'où vous le stockez
        User user = userDAO.getUserById(0); // Suppose que vous avez une méthode pour récupérer l'utilisateur par son ID

        // Ajouter l'objet User à la portée de la requête
        request.setAttribute("user", user);

        // Rediriger vers la page JSP

    }
    
    // Méthode pour encoder l'image en Base64
    private String encodeImage(byte[] image) {
        return java.util.Base64.getEncoder().encodeToString(image);
    }
}
