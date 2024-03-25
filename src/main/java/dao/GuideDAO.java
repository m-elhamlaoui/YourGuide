package dao;

import model.Guide;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class GuideDAO {

    public void addGuide(Guide guide) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try  {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement(
                     "INSERT INTO Guides (first_name, last_name, sex,age, email, phone, city_id, price, password, picture_base64) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, guide.getFirstName());
            preparedStatement.setString(2, guide.getLastName());
            preparedStatement.setString(3, guide.getSex());
            preparedStatement.setInt(4, guide.getAge());
            preparedStatement.setString(5, guide.getEmail());
            preparedStatement.setInt(6, guide.getPhone());
            preparedStatement.setInt(7, guide.getCityId());
            preparedStatement.setDouble(8, guide.getPrice());
            preparedStatement.setString(9, guide.getPassword());
            preparedStatement.setString(10, guide.getPictureBase64());
            

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating guide failed, no rows affected.");
                
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guide.setGuideId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating guide failed, no ID obtained.");
                }
            }

            if (!guide.getLanguages().isEmpty()) {
                addLanguagesForGuide(guide, connection);
            }
            System.out.println("Guide added successfully");
        }finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            
            DatabaseConnector.closeConnection(connection);
        }

    }

    private void addLanguagesForGuide(Guide guide, Connection connection) throws SQLException {
        String sql = "INSERT INTO guide_languages (guide_id, language) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String language : guide.getLanguages()) {
                preparedStatement.setInt(1, guide.getGuideId());
                preparedStatement.setString(2, language);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    public Guide getGuideById(int guideId) throws SQLException {
        Guide guide = new Guide();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Guides WHERE guide_id = ?")) {

            preparedStatement.setInt(1, guideId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    guide =  extractGuideFromResultSet(resultSet);
                }
            }
        }
        return guide;
    }

    public Guide getGuideByEmail(String email) throws SQLException {
        Guide guide = new Guide();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DatabaseConnector.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM Guides WHERE email = ?");
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    guide =  extractGuideFromResultSet(resultSet);
                }
            }
        }finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnector.closeConnection(connection);
        }
        return guide;
    }

    private Guide extractGuideFromResultSet(ResultSet resultSet) throws SQLException {
        Guide guide = new Guide();
        guide.setGuideId(resultSet.getInt("guide_id"));
        guide.setFirstName(resultSet.getString("first_name"));
        guide.setLastName(resultSet.getString("last_name"));
        guide.setSex(resultSet.getString("sex"));
        guide.setAge(resultSet.getInt("age"));
        guide.setEmail(resultSet.getString("email"));
        guide.setPhone(resultSet.getInt("phone"));
        guide.setCityId(resultSet.getInt("city_id"));
        guide.setPrice(resultSet.getDouble("price"));
        guide.setPassword(resultSet.getString("password"));
        guide.setPictureBase64(resultSet.getString("picture_base64"));
        guide.setLanguages(getLanguagesForGuide(resultSet.getInt("guide_id")));
        return guide;
    }

    private List<String> getLanguagesForGuide(int guideId) throws SQLException {
        List<String> languages = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT language FROM guide_languages WHERE guide_id = ?")) {

            preparedStatement.setInt(1, guideId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    languages.add(resultSet.getString("language"));
                }
            }
        }
        return languages;
    }
    public List<Guide> getGuidesByCity(int cityId) throws SQLException {
        List<Guide> guides = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
      

        try {
             connection = DatabaseConnector.getConnection();
              preparedStatement = connection.prepareStatement("SELECT * FROM Guides WHERE city_id = ?");
             Guide guide = new Guide();
             preparedStatement.setInt(1, cityId);
             
             try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    guide =  extractGuideFromResultSet(resultSet);
                    guides.add(guide);

                }
            }

        }  finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            DatabaseConnector.closeConnection(connection);
        }

        return guides;
    }

}
