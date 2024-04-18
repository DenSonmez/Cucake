package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {
    static void changeSaldo(User user, int money, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "UPDATE user SET saldo = (saldo + ?) WHERE username = ?;";

        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, money);
                ps.setString(2, user.getUsername());
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }

    static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM cupcake.user;";

        List<User> userList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    int saldo = rs.getInt("saldo");
                    String email = rs.getString("email");

                    User user = new User(username, password, role, saldo, email);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return userList;
    }

    static User getUserFromUsername(String username, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from user where username = ?";
        User user = null;

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();


                while (rs.next()) {
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    int saldo = rs.getInt("saldo");
                    String email = rs.getString("email");

                    user = new User(username, password, role, saldo, email);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return user;
    }

}
