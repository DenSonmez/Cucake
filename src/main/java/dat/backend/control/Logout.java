package dat.backend.control;

import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {



    //Metoden er deklareret med modifikatoren "public" og har to parametre af typen "HttpServletRequest" og "HttpServletResponse". "HttpServletRequest" er et objekt, der indeholder alle de oplysninger, der kom ind i anmodningen fra klienten, og "HttpServletResponse" er et objekt, der bruges til at sende en HTTP-svar til klienten.
    //
    //I metoden er typen af HTTP-svaret sat til "text/html". Derefter oprettes en ny session ved hjælp af anmodningen, og sessionen ugyldiggøres ved hjælp af "invalidate()" metoden, der nulstiller sessionen. Til sidst omdirigeres brugeren tilbage til "index.jsp" ved hjælp af "sendRedirect()" metoden.
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index");
    }
}