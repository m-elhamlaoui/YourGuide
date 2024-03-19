package com.JAVA.Servlet;

import java.io.IOException;
import java.util.List;

import com.JAVA.Beans.User;
import com.JAVA.DAO.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuideServlet extends HttpServlet{
	
	    private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String ville = request.getParameter("ville");

	        // Retrieve guides from the database
	        UserDAO userDAO = new UserDAO();
	        List<User> guides = userDAO.getGuidesByCity(ville); // Make sure selectedCity is defined

	        // Prepare HTML table
	        StringBuilder htmlResponse = new StringBuilder();
	        htmlResponse.append("<table border='1'>");
	        htmlResponse.append("<tr><th>Nom</th><th>Email</th><th>Sexe</th><th>Age</th></tr>");
	        for (User guide : guides) {
	            htmlResponse.append("<tr>");
	            htmlResponse.append("<td>").append(guide.getName()).append("</td>");
	            htmlResponse.append("<td>").append(guide.getEmail()).append("</td>");
	            htmlResponse.append("<td>").append(guide.getSexe()).append("</td>");
	            htmlResponse.append("<td>").append(guide.getAge()).append("</td>");
	            htmlResponse.append("</tr>");
	        }
	        htmlResponse.append("</table>");

	        // Set response content type
	        response.setContentType("text/html");

	        // Write HTML table to response
	        response.getWriter().println(htmlResponse.toString());
	    }

	    }
	
