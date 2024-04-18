package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "shoppingcart", value = "/shoppingcart")
public class shoppingcart extends HttpServlet {

        private ConnectionPool connectionPool;

        @Override
        public void init() throws ServletException {
            this.connectionPool = ApplicationStart.getConnectionPool();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");


            request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request,response);
    }

    //Først henter den sessionen fra HTTP-anmodningen ved at kalde "request.getSession()" metoden.
    //
    //Dernæst henter den brugeroplysninger ("user") fra sessionen ved at kalde "session.getAttribute()" og passerer "user" som parameter. Hvis der ikke er nogen bruger i sessionen, vil "user" være null.
    //
    //Endelig videresender den HTTP-anmodningen ("request") til "cart.jsp" ved at kalde "request.getRequestDispatcher()" metoden og passerer anmodningen og responsobjekterne videre som parametre.
    //
    //"cart.jsp" er en JSP-fil, der er placeret i WEB-INF mappen, hvilket betyder, at den ikke kan tilgås direkte via en URL-anmodning fra browseren, men kun kan tilgås af andre servletter.
}
