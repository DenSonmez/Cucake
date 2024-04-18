package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveCupcakeOrder", value = "/removecupcakeorder")
public class RemoveCupcakeOrder extends HttpServlet {
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
//doPost()-metoden besvarer HTTP POST-forespørgsler, og den fjerner en cupcake fra en kundes indkøbskurv. Metoden modtager parametre fra HTML-formularen, og derefter henter den "current_order" objektet fra session-objektet. Herefter fjernes cupcake'en fra "current_order" objektet, og brugeren bliver sendt videre til siden "shoppingcart".
//
//Bemærk, at begge metoder bruger response.sendRedirect() til at sende brugeren til en anden side. Dette er en HTTP-redirect, som vil sende en ny anmodning til serveren, og siden vil blive opdateret i overensstemmelse hermed.

        int index = Integer.parseInt(request.getParameter("index"));

        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("current_order");
        order.removeCupcake(index);

        response.sendRedirect("shoppingcart");

    }
}
