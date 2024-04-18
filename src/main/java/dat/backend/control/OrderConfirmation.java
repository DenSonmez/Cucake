package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CupcakeOrder;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.AdminFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeOrderFacade;
import dat.backend.model.persistence.OrderFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "OrderConfirmation", value = "/orderconfirmation")
public class OrderConfirmation extends HttpServlet {

    private ConnectionPool connectionPool;
    /* private Order currentOrder; */

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
      /*  currentOrder = new Order(); */

    }

    //Først hentes den aktuelle session og den nuværende ordre, der er gemt i sessionen. Derefter kontrolleres det, om ordrens mængde er nul. Hvis ja, betyder det, at der ikke er nogen varer i kurven, og derfor omdirigeres brugeren tilbage til forsiden. Hvis ikke, fortsættes behandlingen af anmodningen. Metoden er med andre ord ansvarlig for at validere,
    // om brugeren har en eksisterende ordre med varer, før der gives adgang til checkout-siden.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("current_order");
        if(order.getOrderAmount() == 0){
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        User user = (User)session.getAttribute("user"); // Få brugeren fra sessionen
        List<CupcakeOrder> cupcakes = order.getCupcakes(); // Hent cupcakes fra den nuværende ordre
        int orderID;
        try {
            // Tilføj ordren til databasen og få den genererede ordre-ID tilbage
            orderID = OrderFacade.addOrder(order, connectionPool);
            // Tilføj hver cupcake i ordren til databasen
            for (CupcakeOrder c : cupcakes) {
                CupcakeOrderFacade.addCupcakeOrderToOrder(orderID, c.getPrice(), c.getCupcakeTopId(), c.getCupcakeBottomId(), connectionPool);
            }
            if (user != null) {
                // Hvis brugeren er logget ind og har tilstrækkeligt med saldo på sin konto, træk beløbet for ordren fra brugerens saldo
                if (user.getSaldo() - order.getTotalPrice() >= 0) {
                    AdminFacade.changeSaldo(user, -order.getTotalPrice(), connectionPool);
                    user.setSaldo(user.getSaldo() - order.getTotalPrice());
                }
            }
        } catch (DatabaseException e)
        {
            // Hvis der opstår en databasefejl, send brugeren til en fejlside med en fejlmeddelelse
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        session.setAttribute("confirm_order", order); // Gem den bekræftede ordre i sessionen
        session.removeAttribute("current_order"); // Fjern den nuværende ordre fra sessionen
        order = new Order(); // Opret en ny ordre
        Index.setOrder(order, session); // Opdater den nye ordre i sessionen
        request.getRequestDispatcher("orderconfirmation.jsp").forward(request,response); // Send brugeren til en side med ordrebekræftelse


    }

    //Denne metode behandler POST-anmodninger til servlet'en, og i dette tilfælde videresender den anmodningen til "orderconfirmation.jsp" siden ved hjælp af RequestDispatcher. Det betyder, at når brugeren har afgivet en ordre, og POST-anmodningen behandles, vil servlet'en vise en ordrebekræftelsesside til brugeren. Det er ikke muligt at få mere information om, hvad der sker i servlet'en,
    // da denne metode kun har ansvaret for at videresende anmodningen og ikke har nogen kode.

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("orderconfirmation.jsp").forward(request,response);
    }
}
