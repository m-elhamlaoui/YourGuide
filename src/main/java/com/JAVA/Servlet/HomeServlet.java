package com.JAVA.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.JAVA.Beans.User;
import com.JAVA.DAO.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer la ville sélectionnée
        String selectedCity = request.getParameter("ville");

        // Utiliser le DAO pour récupérer les guides de la ville sélectionnée
        UserDAO userDao = new UserDAO();
        List<User> guides = userDao.getGuidesByCity(selectedCity);
        
        Set<String> cities = userDao.getAllCities();

        // Pour chaque ville, vérifiez si des guides existent dans cette ville
        for (String city : cities) {
            if (userDao.guidesExistInCity(city)) {
                request.setAttribute(city, true);
            }
        }
        // Envoyer les guides récupérés à la page JSP pour affichage
        request.setAttribute("guides", guides);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
