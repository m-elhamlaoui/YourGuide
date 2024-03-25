package servlet;

import dao.CityDAO;
import dao.GuideDAO;
import model.City;
import model.Guide;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;


import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;




@WebServlet(name = "SignUpServlet", urlPatterns = {"/signup"})
public class SignUpServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         


        try {
               // Retrieve parameters from the request
         String firstName = request.getParameter("firstName");
         String lastName = request.getParameter("lastName");
         System.out.println("First Name: " + firstName);
         System.out.println("Last Name: " + lastName);
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
    //             if (firstName == null || lastName == null || sex == null || email == null || password == null || languages == null || cityName == null) {
    //                 System.out.println("First Name: " + firstName);
                // Add the guide to the database
                GuideDAO guideService = new GuideDAO();
                if (guideService.getGuideByEmail(email) != null) {
                    
                    
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
    