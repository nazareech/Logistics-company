package com.example.logisticscompany;

import java.sql.*;


public class DatabaseHeandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost +
                ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public void singUpUser(User user) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USERS_USERNAME + ", " +
                Const.USERS_PASSWORD + ", " +  // Додано кому
                Const.USERS_EMAIL + ", " +
                Const.USERS_ROLE_ID + ", " +
                Const.USERS_GENDER + ") " +    // Додано пробіл перед VALUES
                "VALUES (?, ?, ?, ?, ?)";      // Додано пробіли для читабельності

        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.setString(5, user.getGender());

            preparedStatement.executeUpdate();
            System.out.println("Successfully registered user with username: " + user.getUsername() + " and email: " + user.getEmail() + ".");
        } catch (SQLException e) {
            // Краще логування помилки
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("Failed query: " + insert);
            throw new RuntimeException("Failed to register user", e);
        }
    }


    public ResultSet getUsers(User user) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USERS_USERNAME + " =? AND " +
                Const.USERS_PASSWORD + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement.executeQuery();
    }

}
