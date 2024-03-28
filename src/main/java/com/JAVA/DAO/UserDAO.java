package com.JAVA.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.JAVA.Beans.User;

public class UserDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "Zinebch12@";
    private String jdbcDriver = "org.postgresql.Driver";

    private static final String INSERT_USER_SQL = "INSERT INTO users" + "(name, email, password, sexe, age, ville, tel,picture,langue,tarif) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
    private static final String SELECT_ALL_CITIES_SQL = "SELECT DISTINCT ville FROM users";
    private static final String SELECT_USER_BY_ID = "SELECT id, name, email, password, sexe, age, ville, tel,picture,langue,tarif FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name=?, email=?, password=?, sexe=?, age=?, ville=?, tel=?,picture=?,langue=?,tarif=? WHERE id=?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT id, name, password, email FROM users WHERE email = ?";
    private static final String GUIDES_EXIST_IN_CITY_SQL = "SELECT COUNT(*) FROM users WHERE ville = ?";
    private static final String SELECT_ALL_LANGUAGES_SQL = "SELECT DISTINCT langue FROM users";

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

            preparedStatement.setBytes(8, user.getPicture());
         
            preparedStatement.setString(9, user.getLangue()); // Set the comma-separated string in the PreparedStatement
            preparedStatement.setInt(10, user.getTarif());

            preparedStatement.executeUpdate();
        }
        
    }
    public List<User> getGuidesByCity(String city) throws DAOException {
        List<User> guides = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM users WHERE ville = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, city);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String sexe = resultSet.getString("sexe");
                String age = resultSet.getString("age");
                String ville = resultSet.getString("ville");
                String tel = resultSet.getString("tel");
                byte[] picture = resultSet.getBytes("picture");
             // Récupération de la liste de langues
                String langue = resultSet.getString("langue");

                int tarif = resultSet.getInt("tarif");
                User guide = new User(id, name, email, password, sexe, age, ville, tel, picture, langue,tarif);
                guides.add(guide);
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving guides by city: " + e.getMessage(), e);
        } finally {
            close(resultSet, statement, connection);
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

                byte[] picture = rs.getBytes("picture");
                String langues = rs.getString("langues");

             
                int tarif = rs.getInt("tarif");
                
                user=new User(id, name, email, password, sexe, age, ville, tel, picture, langues,tarif);
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
                byte[] picture = rs.getBytes("picture");
                String langues = rs.getString("langues");

                
                int tarif=rs.getInt("tarif");
                
                users.add(new User(id, name, email, password, sexe, age, ville, tel, picture, langues,tarif));
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
            statement.setBytes(9, user.getPicture());;
            statement.setInt(10, user.getTarif());
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
    protected void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                // Gérer l'exception ou afficher un message d'erreur
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // Gérer l'exception ou afficher un message d'erreur
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Gérer l'exception ou afficher un message d'erreur
                e.printStackTrace();
            }
        }
    }
    public Set<String> getAllLanguages() throws DAOException {
        Set<String> languages = new HashSet<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LANGUAGES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Supposons que les langues soient stockées dans une colonne nommée "langue"
                languages.add(resultSet.getString("langue"));
            }
        } catch (SQLException e) {
            throw new DAOException("Error retrieving all languages: " + e.getMessage(), e);
        }

        return languages;
    }
    public User getUserById(int id) throws DAOException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Log pour indiquer que l'utilisateur a été trouvé dans la base de données
                    System.out.println("Utilisateur trouvé dans la base de données avec l'ID : " + id);
                    // Extraction des données de l'utilisateur
                    user = extractUser(resultSet);
                } else {
                    // Log pour indiquer que l'utilisateur n'a pas été trouvé dans la base de données avec l'ID spécifié
                    System.out.println("Aucun utilisateur trouvé dans la base de données avec l'ID : " + id);
                }
            }
        } catch (SQLException e) {
            // Log pour indiquer une exception lors de la récupération de l'utilisateur par ID
            System.out.println("Erreur lors de la récupération de l'utilisateur par ID : " + e.getMessage());
            throw new DAOException("Error retrieving user by ID: " + e.getMessage(), e);
        }
        return user;
    }

    private User extractUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sexe = resultSet.getString("sexe");
        String age = resultSet.getString("age");
        String ville = resultSet.getString("ville");
        String tel = resultSet.getString("tel");
        byte[] picture = resultSet.getBytes("picture");
        String langues = resultSet.getString("langues");
        
         int tarif=resultSet.getInt("tarif");
        
        return new User(id, name, email, password, sexe, age, ville, tel, picture, langues,tarif);
    }
}
