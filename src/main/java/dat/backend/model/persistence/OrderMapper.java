package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {


    static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from orders;";

        List<Order> orderList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    int totalPrice = rs.getInt("total_price");
                    Timestamp timestamp = rs.getTimestamp("order_creation_timestamp");
                    String username = rs.getString("username");
                    boolean isOrderActive = rs.getBoolean("is_order_active");
                    int orderAmount = rs.getInt("order_amount");


                    Order order = new Order(orderId, totalPrice, timestamp, username, isOrderActive, orderAmount);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return orderList;
    }

    static int addOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO orders (username, order_amount, total_price) values (?,?,?);";
        int orderId = 0;
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getUsername());
                ps.setInt(2, order.getOrderAmount());
                ps.setInt(3, order.getTotalPrice());
                int rowsAffected = ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    orderId = rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex, "Could not insert item into database");
        }
        return orderId;
    }

    static void deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM orders WHERE order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, orderId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }


}

