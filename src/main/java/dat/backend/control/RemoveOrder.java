package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveOrder", value = "/removeorder")
public class RemoveOrder extends HttpServlet {
    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("index");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int orderId = Integer.parseInt(request.getParameter("orderid"));

        try {
            CupcakeOrderFacade.removeCupcakeOrdersById(orderId, connectionPool);
            OrderFacade.deleteOrder(orderId, connectionPool);
        }
        catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }




        response.sendRedirect("admin");

    }
}

//Denne kode viser en metode kaldet "doPost", der bruger HttpServletRequest og HttpServletResponse som input og kan kaste undtagelser af typen ServletException og IOException.
//
//Metoden starter med at hente værdien af parameteren "orderid" fra request-objektet og konverterer denne værdi til en integer.
//
//Herefter forsøger metoden at slette ordren fra databasen ved at kalde to forskellige metoder, "removeCupcakeOrdersById" og "deleteOrder", der ligger i de respektive facades, "CupcakeOrderFacade" og "OrderFacade". Begge metoder modtager orderId og en "connectionPool" som input.
//
//Hvis der opstår en undtagelse af typen "DatabaseException" i forsøget på at slette ordren, bliver en fejlbesked sat som attribut på request-objektet, og metoden redirecter til siden "error.jsp" ved hjælp af requestDispatcher.
//
//Til sidst i metoden bliver der sendt en redirect til "admin" siden via response-objektet.
//
//Alt i alt ser det ud til, at denne metode sletter en ordre fra databasen og håndterer eventuelle undtagelser ved at vise en fejlbesked og sende en redirect til en anden side.
