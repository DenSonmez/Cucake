package dat.backend.model.persistence;

import dat.backend.model.entities.CupcakeOrder;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class CupcakeOrderFacade {
    public static List<CupcakeOrder> getAllCupcakeOrders(ConnectionPool connectionPool) throws DatabaseException {
        return CupcakeOrderMapper.getAllCupcakeOrders(connectionPool);
    }

    public static CupcakeOrder getCupcakeOrderFromOrderId(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        return CupcakeOrderMapper.getCupcakeOrderFromOrderId(order_id, connectionPool);
    }

    public static boolean addCupcakeOrderToOrder(int order_id, int price, int top_id, int bottom_id, ConnectionPool connectionPool) throws DatabaseException {
        return CupcakeOrderMapper.addCupcakeOrderToOrder(order_id, price, top_id, bottom_id, connectionPool);
    }

    public static void removeCupcakeOrdersById(int order_id, ConnectionPool connectionPool) throws DatabaseException {
        CupcakeOrderMapper.removeCupcakeOrdersById(order_id, connectionPool);
    }
}
