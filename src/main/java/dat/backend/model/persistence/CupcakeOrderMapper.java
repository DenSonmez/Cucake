package dat.backend.model.persistence;

import dat.backend.model.entities.CupcakeOrder;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeOrderMapper {

    static List<CupcakeOrder> getAllCupcakeOrders(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from cupcake_order;";

        List<CupcakeOrder> cupcakeOrderList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeorderId = rs.getInt("cupcake_order_id");
                    int orderId = rs.getInt("order_id");
                    int price = rs.getInt("price");
                    int cupcakeTopId = rs.getInt("cupcake_top_id");
                    int cupcakeBottomId = rs.getInt("cupcake_bottom_id");
                    CupcakeOrder cupcakeOrder = new CupcakeOrder(cupcakeorderId, orderId, price, cupcakeTopId, cupcakeBottomId);
                    cupcakeOrderList.add(cupcakeOrder);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcakeOrderList;
    }

    static CupcakeOrder getCupcakeOrderFromOrderId(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from cupcake_order where order_id = ?";
        CupcakeOrder cupcakeOrder = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeorderId = rs.getInt("cupcake_order_id");
                    int orderId = rs.getInt("order_id");
                    int price = rs.getInt("price");
                    int cupcakeTopId = rs.getInt("cupcake_top_id");
                    int cupcakeBottomId = rs.getInt("cupcake_bottom_id");
                    cupcakeOrder = new CupcakeOrder(cupcakeorderId, orderId, price, cupcakeTopId, cupcakeBottomId);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcakeOrder;
    }

    static boolean addCupcakeOrderToOrder(int order_id, int price, int top_id, int bottom_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO cupcake_order (order_id, price, cupcake_top_id, cupcake_bottom_id) VALUES (?,?,?,?); ";
        boolean retBool = false;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ps.setInt(2, price);
                ps.setInt(3, top_id);
                ps.setInt(4, bottom_id);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    retBool = true;
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return retBool;
    }

    static void removeCupcakeOrdersById(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "DELETE FROM cupcake_order WHERE order_id = ?;";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, order_id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
    }
}
