package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.AdminFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrderFacade;
import dat.backend.model.persistence.OrderMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", value = "/admin")
public class Admin extends HttpServlet {

    private ConnectionPool connectionPool;

    //Metoden "init()" bliver kaldt, når servletten starter. I denne metode initialiseres en forbindelsespool til databasen ved at kalde metoden "getConnectionPool()" fra "ApplicationStart" - en klasse i webapplikationen. Forbindelsespoolen kan derefter bruges til at oprette forbindelse til databasen og udføre databaserelaterede operationer.
    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    //Denne metode definerer en GET-request, som håndterer en anmodning til en side, hvor en administrator kan se en liste over alle brugere og ordrer i systemet.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Først hentes brugerobjektet fra sessionsattributterne for at sikre, at brugeren er logget ind og er en gyldig bruger.
        // Hvis brugeren er logget ind, og brugerens rolle er "admin", hentes en liste over alle brugere og ordrer i systemet. Hvis der opstår en fejl under databasen,
        // bliver der kastet en DatabaseException, og brugeren bliver viderestillet til en fejlsiden "error.jsp".
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                List<User> users = null;
                List<Order> orders = null;
                try {
                    users = AdminFacade.getAllUsers(connectionPool);
                    orders = OrderFacade.getAllOrders(connectionPool);
                } catch (DatabaseException e)
                {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }

                //Brugerliste og ordreliste bliver sat som attributter i anmodningen, og anmodningen videresendes til "WEB-INF/admin.jsp", hvor de to lister bliver vist.
                request.setAttribute("userList", users);
                request.setAttribute("orderList",orders);
                request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request, response);

            }
        }
//Hvis brugeren ikke er logget ind som administrator, vil en besked blive sat som attribut i anmodningen og derefter videresendt til startsiden "index.jsp".
        request.setAttribute("besked", "du er ikke en admin");
        request.getRequestDispatcher("index").forward(request, response);

    }

    //Denne metode definerer en POST-request, som håndterer en anmodning om at ændre en brugers saldo.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Først hentes brugerobjektet fra sessionsattributterne for at sikre, at brugeren er logget ind og er en gyldig bruger. Hvis brugeren ikke er logget ind som administrator, vil en besked blive sat som attribut i anmodningen,
        // og brugeren vil blive viderestillet til startsiden "index.jsp".
        User _user = (User) request.getSession().getAttribute("user");
        if (!_user.getRole().equalsIgnoreCase("admin")) {

            request.setAttribute("besked", "du er ikke en admin");
            request.getRequestDispatcher("index").forward(request, response);

        }
//Herefter hentes brugernavnet og det beløb, der skal tilføjes eller trækkes fra brugerens saldo, fra anmodningsparametrene.

        String username = request.getParameter("user");
        int money = Integer.parseInt(request.getParameter("money"));
//// Der udføres en søgning i databasen efter brugeren ved hjælp af brugernavnet.
        try {
            User user = AdminFacade.getUserFromUsername(username, connectionPool);

            //Hvis brugeren findes i databasen, bliver brugerens saldo ændret ved hjælp af AdminFacade.changeSaldo-metoden, og brugerens saldo bliver opdateret i brugerobjektet. Hvis der opstår en fejl under databasen,
            // bliver der kastet en DatabaseException, og brugeren bliver viderestillet til en fejlsiden "error.jsp".
            AdminFacade.changeSaldo(user, money, connectionPool);
            user.setSaldo(user.getSaldo() + money);

        } catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }



//Til sidst bliver brugeren viderestillet til administrationspanelet, hvor en liste over alle brugere og ordrer i systemet kan ses.
        response.sendRedirect("admin");
    }

}

