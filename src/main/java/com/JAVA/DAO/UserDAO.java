package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.JAVA.Beans.User;

public class UserDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "Zinebch12@";
    private String jdbcDriver = "org.postgresql.Driver";

    private static final String INSERT_USER_SQL = "INSERT INTO users" + "(name, email, password, sexe, age, ville, tel) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_CITIES_SQL = "SELECT DISTINCT ville FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, password, sexe, age, ville, tel FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name=?, email=?, password=?, sexe=?, age=?, ville=?, tel=? WHERE id=?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT id, name, password, email FROM users WHERE email = ?";
    private static final String SELECT_GUIDES_BY_CITY_SQL = "SELECT name,email,sexe,tel,age FROM users WHERE ville = ?";
    private static final String GUIDES_EXIST_IN_CITY_SQL = "SELECT COUNT(*) FROM users WHERE ville = ?";

    public UserDAO() {

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
    
    public Set<String> getAllCities() throws DAOException {
        Set<String> cities = new HashSet<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                cities.add(resultSet.getString("ville"));
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving all cities: " + e.getMessage(), e);
        }

        return cities;
    }

    public boolean guidesExistInCity(String city) throws DAOException {
        boolean guidesExist = false;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GUIDES_EXIST_IN_CITY_SQL)) {

            preparedStatement.setString(1, city);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    guidesExist = (count > 0);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error checking if guides exist in city: " + e.getMessage(), e);
        }

        return guidesExist;
    }
    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSexe());
            preparedStatement.setString(5, user.getAge());
            preparedStatement.setString(6, user.getVille());
            preparedStatement.setString(7, user.getTel());

            preparedStatement.executeUpdate();
        }
        
    }
    public List<User> getGuidesByCity(String ville) throws DAOException {
        List<User> guides = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GUIDES_BY_CITY_SQL)) {

            preparedStatement.setString(1, ville);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User guide = new User();
                    guide.setName(resultSet.getString("name"));
                    guide.setEmail(resultSet.getString("email"));
                    guide.setTel(resultSet.getString("tel"));
                    guide.setSexe(resultSet.getString("sexe"));
                    
                    guides.add(guide);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving guides by city: " + e.getMessage(), e);
        }

        return guides;
    }
    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String sexe = rs.getString("sexe");
                String age = rs.getString("age");
                String ville = rs.getString("ville");
                String tel = rs.getString("tel");

                user = new User(id, name, email, password, sexe, age, ville, tel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String sexe = rs.getString("sexe");
                String age = rs.getString("age");
                String ville = rs.getString("ville");
                String tel = rs.getString("tel");

                users.add(new User(id, name, email, password, sexe, age, ville, tel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getSexe());
            statement.setString(5, user.getAge());
            statement.setString(6, user.getVille());
            statement.setString(7, user.getTel());
            statement.setInt(8, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public User getUserByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeResources(preparedStatement, resultSet, connection);
        }

        return user;
    }

    private void closeResources(PreparedStatement preparedStatement, ResultSet resultSet, Connection connection) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // Handle or log the exception
        }
    }


    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
