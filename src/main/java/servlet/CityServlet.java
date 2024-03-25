package servlet;
import model.City;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import dao.CityDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "CityServlet", urlPatterns = {"/city"})
public class CityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve city information from the form
        String cityName = request.getParameter("city_name");
        int zipCode =Integer.parseInt(request.getParameter("zip_code")) ;

        // Create a new City object
        City city = new City();
        city.setCityName(cityName);
        city.setZipCode(zipCode);

        // Add the city to the database
        CityDAO cityService = new CityDAO();
        try {
            cityService.addCity(city);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     

        // Redirect to a success page
        response.sendRedirect("city-success.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the city name parameter from the request
        String cityName = request.getParameter("name");

        // Create a CityService instance
        CityDAO cityService = new CityDAO();

        // Retrieve the city by name
        City city;
        try {
            city = cityService.getCityByName(cityName);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       

        // Set response content type
        response.setContentType("text/html");

        // Get the PrintWriter object to write the HTML response
        PrintWriter out = response.getWriter();

        // Write the HTML response
        out.println("<html><body>");

        // If the city is found, display its information
        // if (city != null) {
        //     out.println("<h2>City Information:</h2>");
        //     out.println("<p>Name: " + city.getCityName() + "</p>");
        //     out.println("<p>Postal Code: " + city.getZipCode() + "</p>");
        // } else {
        //     out.println("<p>City not found!</p>");
        // }

        // out.println("</body></html>");
    }

    
}
