package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import dao.CityDAO;
import model.City;

@WebServlet(name =  "CityListServlet" ,urlPatterns= {"/CityList"})
public class CityListServlet extends HttpServlet {
 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Initialize DAO instance
        CityDAO cityDAO = new CityDAO();

        try {
            // Get all cities from the database
            List<City> cities = cityDAO.getAllCities();
            for (City city : cities) {
                System.out.println("City"+ city.getCityName());
            }
            // Set attribute to be forwarded to the JSP
            request.setAttribute("cities", cities);

            // Forward the request to the CityList.jsp for rendering
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            // Handle database-related exceptions
            e.printStackTrace();
            // Optionally, you can redirect to an error page
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
