package dat.backend.model.persistence;

import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class OrderFacade {
    public static int addOrder(Order order, ConnectionPool connectionPool) throws DatabaseException {
        return OrderMapper.addOrder(order, connectionPool);
    }
    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {

        return OrderMapper.getAllOrders(connectionPool);
    }
    public static void deleteOrder(int orderId, ConnectionPool connectionPool) throws DatabaseException {
        OrderMapper.deleteOrder(orderId, connectionPool);
    }
}
