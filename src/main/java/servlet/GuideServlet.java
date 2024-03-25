package servlet;
import model.City;

import model.Guide;
import dao.CityDAO;
import dao.GuideDAO;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "GuideServlet", urlPatterns = {"/guide"})
public class GuideServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("guide.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            int age = Integer.parseInt(request.getParameter("age"));
            String sex = request.getParameter("sex");
            
            double price = Double.parseDouble(request.getParameter("price"));
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            byte[] profilePic = request.getParameter("profilePic").getBytes();
            String pictureBase64 = Base64.getEncoder().encodeToString(profilePic);
            int phone = Integer.parseInt(request.getParameter("phone"));
            List<String> languages = Arrays.asList(request.getParameter("languages").split(","));
            String cityName = request.getParameter("cityName");
            int zipCode = Integer.parseInt(request.getParameter("zipCode"));

            // Validate input parameters
            if (firstName == null || lastName == null || sex == null || email == null || password == null || languages == null || cityName == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            // Create a new City object or retrieve an existing one
            CityDAO cityService = new CityDAO();
            City city = cityService.getCityByName(cityName);
            if (city == null) {
                city = new City(cityName, zipCode);
                cityService.addCity(city);
                city = cityService.getCityByName(cityName);
            }

            // Create a new Guide object with the provided information
            Guide guide = new Guide();
            guide.setFirstName(firstName);
            guide.setLastName(lastName);
            guide.setAge(age);
            guide.setSex(sex);
            guide.setPrice(price);
            guide.setEmail(email);
            guide.setPassword(password);
            guide.setProfilePic(profilePic);
            guide.setPictureBase64(pictureBase64);
            guide.setPhone(phone);
            guide.setLanguages(languages);
            guide.setCityId(city.getCityId()); // Assuming getId() method exists in City model
            

            System.out.println("First Name: " + guide.getFirstName());
System.out.println("Last Name: " + guide.getLastName());
System.out.println("Age: " + guide.getAge());
System.out.println("Sex: " + guide.getSex());
System.out.println("Price: " + guide.getPrice());
System.out.println("Email: " + guide.getEmail());
System.out.println("Password: " + guide.getPassword());
System.out.println("Profile Picture: " + guide.getProfilePic());
System.out.println("Base64 Picture: " + guide.getPictureBase64());
System.out.println("Phone: " + guide.getPhone());
System.out.println("Languages: " + guide.getLanguages());
System.out.println("City ID: " + city.getCityId());
            // Add the guide to the database
            GuideDAO guideService = new GuideDAO();
            guideService.addGuide(guide);

            // Redirect to a success page
            response.sendRedirect("signup-success.jsp");
        } catch (NumberFormatException | SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
