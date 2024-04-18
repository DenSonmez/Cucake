package dat.backend.model.persistence;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;

import java.util.List;

public class AdminFacade {
   public static void changeSaldo(User user, int money, ConnectionPool connectionPool) throws DatabaseException {
    AdminMapper.changeSaldo(user, money, connectionPool);
    }

    public static List<User> getAllUsers(ConnectionPool connectionPool) throws DatabaseException {
       return AdminMapper.getAllUsers(connectionPool);
    }
    public static User getUserFromUsername(String username, ConnectionPool connectionPool) throws DatabaseException{
       return AdminMapper.getUserFromUsername(username, connectionPool);
    }
}
