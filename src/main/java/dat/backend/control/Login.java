package dat.backend.control;

import com.mysql.cj.Session;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"} )
public class Login extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //doGet-metoden sendes brugeren tilbage til forsiden, hvis der forsøges at sende en GET-anmodning til denne servlet.
        response.sendRedirect("index.jsp");
    }

    //I doPost-metoden får servletten brugernavnet og adgangskoden fra HTML-formularen,
    // der blev indsendt, og prøver at logge brugeren ind ved hjælp af UserFacade.login-metoden. Hvis login er vellykket, gemmes brugeren i sessionen, og brugerens e-mailadresse hentes også fra sessionen og gemmes også.
    // Derudover tildeler setOrder-metoden den aktuelle ordre til den nyindloggede bruger og sender brugeren tilbage til forsiden. Hvis der opstår en databasefejl under login, videresendes anmodningen til en fejlside.

    //Bemærk at UserFacade og Order er klasser, der formodentlig indeholder metoder, der interagerer med databasen for at hente bruger- og ordredata.

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.setAttribute("user", null); // invalidating user object in session scope
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = (String) request.getSession().getAttribute("email");

        try
        {
            User user = UserFacade.login(username, password, connectionPool);
            session = request.getSession();
            session.setAttribute("user", user); // adding user object to session scope
            session.setAttribute("email", user.getEmail());// adding user email object to session scope
            Order order = (Order) session.getAttribute("current_order");// adding current_ordre object to session scope
            order.setUser(user);
            Index.setOrder(order, session);

            response.sendRedirect("index");
        }
        catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}