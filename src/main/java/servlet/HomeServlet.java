package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.Guide;
import model.City;
import dao.GuideDAO;
import dao.CityDAO;

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

        String selectedCityName = request.getParameter("city_name");
        CityDAO cityDAO = new CityDAO();
        City selectedCity = new City();
        try {
            selectedCity = cityDAO.getCityByName(selectedCityName);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // Utiliser le DAO pour récupérer les guides de la ville sélectionnée
        GuideDAO userDao = new GuideDAO();
        List<Guide> guides = new ArrayList<>() ;
        try {
            guides = userDao.getGuidesByCity(selectedCity.getCityId());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            List<City> cities = cityDAO.getAllCities();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Pour chaque ville, vérifiez si des guides existent dans cette ville
        // for (City city : cities) {
        //     if (userDao.guidesExistInCity(city)) {
        //         request.setAttribute(city, true);
        //     }
        // }
        // Envoyer les guides récupérés à la page JSP pour affichage
        request.setAttribute("guides", guides);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
    }
}
