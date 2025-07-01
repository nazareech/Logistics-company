package com.example.logisticscompany;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseHeandler extends Configs{
    Connection dbConnection;

//    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
//        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
//        return dbConnection;
//    }

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost +
                ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(connectionString, dbUser, dbPass);
    }

    public void singUpUser(String username, String password, String email,
                           String role, String gender) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USERS_USERNAME + ", " +
                Const.USERS_PASSWORD + ", " +  // Додано кому
                Const.USERS_EMAIL + ", " +
                Const.USERS_ROLE_ID + ", " +
                Const.USERS_GENDER + ") " +    // Додано пробіл перед VALUES
                "VALUES (?, ?, ?, ?, ?)";      // Додано пробіли для читабельності

        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, gender);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Краще логування помилки
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("Failed query: " + insert);
            throw new RuntimeException("Failed to register user", e);
        }
    }


}
