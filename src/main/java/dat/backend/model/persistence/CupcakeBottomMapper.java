package dat.backend.model.persistence;

import dat.backend.model.entities.CupcakeBottom;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CupcakeBottomMapper {
    static List<CupcakeBottom> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from cupcake_bottom;";

        List<CupcakeBottom> cupcakeBottomList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeBottomId = rs.getInt("cupcake_bottom_id");
                    int price = rs.getInt("price");
                    String flavor = rs.getString("flavor");

                    CupcakeBottom cupcakeBottom = new CupcakeBottom(cupcakeBottomId, price, flavor);
                    cupcakeBottomList.add(cupcakeBottom);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcakeBottomList;
    }

    static CupcakeBottom getBottomFromId(int bottom_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from cupcake_bottom where cupcake_bottom_id = ?";
        CupcakeBottom cupcakeBottom = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bottom_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeBottomId = rs.getInt("cupcake_bottom_id");
                    int price = rs.getInt("price");
                    String flavor = rs.getString("flavor");
                    cupcakeBottom = new CupcakeBottom(cupcakeBottomId, price, flavor);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcakeBottom;
    }

    static String getBottomFlavorFromId(int bottom_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select flavor from cupcake_bottom where cupcake_bottom_id = ?";
        String flavor = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, bottom_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    flavor = rs.getString("flavor");

                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return flavor;
    }
}

