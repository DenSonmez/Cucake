package dat.backend.model.persistence;

import dat.backend.model.entities.CupcakeTop;
import dat.backend.model.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeTopMapper {
    static List<CupcakeTop> getAllTops(ConnectionPool connectionPool) throws DatabaseException {

        String sql = "select * from cupcake_top;";

        List<CupcakeTop> cupcakeTopList = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcakeTopId = rs.getInt("cupcake_top_id");
                    int price = rs.getInt("price");
                    String flavor = rs.getString("flavor");

                    CupcakeTop cupcakeTop = new CupcakeTop(cupcakeTopId, price, flavor);
                    cupcakeTopList.add(cupcakeTop);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcakeTopList;
    }

    static CupcakeTop getTopFromId(int top_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select * from cupcake_top where cupcake_top_id = ?";
        CupcakeTop cupcaketop = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, top_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int cupcaketopId = rs.getInt("cupcake_top_id");
                    int price = rs.getInt("price");
                    String flavor = rs.getString("flavor");
                    cupcaketop = new CupcakeTop(cupcaketopId, price, flavor);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException(e, "Fejl i tilgangen til databasen");
        }
        return cupcaketop;
    }

    static String getTopFlavorFromId(int top_id, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select flavor from cupcake_top where cupcake_top_id = ?";
        String flavor = null;
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, top_id);
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
