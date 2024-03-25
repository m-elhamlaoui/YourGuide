package servlet;
import java.io.IOException;
import java.sql.SQLException;

import model.Guide;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;

import dao.GuideDAO;


@WebServlet( name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user input from the login form
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        GuideDAO guidegervice = new GuideDAO();
        Guide guide = new Guide();
        try {
            guide = guidegervice.getGuideByEmail(email);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Check if the provided credentials are valid

        if (guide.getPassword().equals(password)) {
            // Authentication successful
            // Create a session for the user
            request.getSession().setAttribute("email", password);
            // Redirect the user to a welcome page or a secured area
            response.sendRedirect("welcome.jsp");
        } else {
            // Authentication failed
            // Redirect the user back to the login page with an error message
            response.sendRedirect("login.jsp?error=true");

        }


    request.getRequestDispatcher("home.jsp").forward(request, response);
    }

}
