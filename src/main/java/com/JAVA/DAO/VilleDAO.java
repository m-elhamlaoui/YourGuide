package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.JAVA.Beans.User;


public class VilleDAO {
	private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "Zinebch12@";
    private String jdbcDriver = "org.postgresql.Driver";
    private static final String INSERT_Ville_SQL = "INSERT INTO ville" + "(name) VALUES (?)";
    private static final String SELECT_ALL_Villes = "SELECT * FROM ville";

    public VilleDAO() {

    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addVille(String ville) throws DAOException, SQLException {
    	        try (Connection connection = getConnection();
    	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Ville_SQL)) {

    	        	// Créer un objet User
                    User user = new User();
                   
                    user.setVille(ville);
                  
    	            preparedStatement.setString(1, user.getVille());

    	            preparedStatement.executeUpdate();
    	        }
    	    }
    
    public List<String> getAllVilles() throws SQLException {
        List<String> villes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Villes)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                villes.add(rs.getString("name"));
            }
        }
        return villes;
    }

    

}

