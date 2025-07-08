package database;

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
                Const.USERS_PASSWORD + ", " +
                Const.USERS_EMAIL + ", " +
                Const.USERS_ROLE_ID + ", " +
                Const.USERS_GENDER + ",  " +
                Const.USERS_FIRST_NAME + ", " +
                Const.USERS_LAST_NAME + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getDbConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insert)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getRoleId());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getFirstName());
            preparedStatement.setString(7, user.getLastName());

            preparedStatement.executeUpdate();
            System.out.println("Successfully registered user with username: " + user.getUsername() + " and email: " + user.getEmail() + ".");
        } catch (SQLException e) {
            // Краще логування помилки
            System.err.println("SQL Error: " + e.getMessage());
            System.err.println("Failed query: " + insert);
            throw new RuntimeException("Failed to register user", e);
        }
    }


    public ResultSet chekUsers(User loginUser) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + Const.USER_TABLE +
                " WHERE " + Const.USERS_USERNAME + " =? AND " +
                Const.USERS_PASSWORD + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, loginUser.getUsername());
        preparedStatement.setString(2, loginUser.getPassword());

        try (ResultSet rs = preparedStatement.executeQuery()) {
            if (rs.next()) {
                CurrentUser user = CurrentUser.getInstance();
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password_hash"));
                user.setEmail(rs.getString("email"));
                user.setRoleId(rs.getInt("role_id"));
                user.setGender(rs.getString("gender"));
                return preparedStatement.executeQuery();
            }
            return null;
        }
    }
}
