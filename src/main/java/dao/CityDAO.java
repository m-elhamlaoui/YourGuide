package dao;

import model.City;
import util.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {

    // Method to add a new city to the database
    public void addCity(City city) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Cities (city_name, zip_code) VALUES (?, ?)")) {
            preparedStatement.setString(1, city.getCityName());
            preparedStatement.setInt(2, city.getZipCode());
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve a city by its name
    public City getCityByName(String cityName) throws SQLException {
        City city = null;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cities WHERE city_name = ?")) {
            preparedStatement.setString(1, cityName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    city = new City();
                    city.setCityId(resultSet.getInt("city_id"));
                    city.setCityName(resultSet.getString("city_name"));
                    city.setZipCode(resultSet.getInt("zip_code"));
                }
            }
        }
        return city;
    }

    // Method to retrieve all cities
    public List<City> getAllCities() throws SQLException {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cities")) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    City city = new City();
                    city.setCityId(rs.getInt("city_id"));
                    city.setCityName(rs.getString("city_name"));
                    city.setZipCode(rs.getInt("zip_code"));
                    cities.add(city);
                }
            }
        } 
        return cities;
    }
}
