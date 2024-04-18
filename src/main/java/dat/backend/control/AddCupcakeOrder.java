package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CupcakeBottom;
import dat.backend.model.entities.CupcakeOrder;
import dat.backend.model.entities.CupcakeTop;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeBottomFacade;
import dat.backend.model.persistence.CupcakeTopFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.zip.DataFormatException;

@WebServlet(name = "AddCupcakeOrder", value = "/AddCupcakeOrder")
public class AddCupcakeOrder extends HttpServlet
{
    private ConnectionPool connectionPool;

////Metoden "init()" bliver kaldt, når servlet starter.
//// I denne metode initialiseres en forbindelsespool til databasen ved at kalde metoden "getConnectionPool()" fra "ApplicationStart" - en klasse i webapplikationen.
//// Forbindelsespoolen kan derefter bruges til at oprette forbindelse til databasen og udføre databaserelaterede operationer.
    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    //Metoden "doGet()" bliver kaldt, når der sendes en GET-anmodning til servlet.
    // I dette tilfælde omdirigeres anmodningen til "index" -siden ved hjælp af metoden "sendRedirect()". Dette betyder, at servlet ikke udfører nogen operationer,
    // når der sendes en GET-anmodning til servlet.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.sendRedirect("index");
    }




//Metoden "doPost()" bliver kaldt, når der sendes en POST-anmodning til servlet. Denne metode starter med at initialisere to variable, "bottomID" og "topID", til -1. Disse variable vil blive brugt senere i metoden til at gemme ID'erne på bunden og toppen af cupcaken, som skal tilføjes til ordren.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int bottomID = -1;
        int topID = -1;

        //Metoden forsøger derefter at hente ID'erne på bunden og toppen af cupcaken fra anmodningen ved hjælp af metoden "getParameter()" på "request"-objektet.
        // Hvis ID'erne ikke kan parses som integer, fanges "NumberFormatException" og anmodningen omdirigeres til "index.jsp" ved hjælp af "getRequestDispatcher().forward()".
        try {
            bottomID = Integer.parseInt(request.getParameter("bottom"));
            topID = Integer.parseInt(request.getParameter("top"));
        }
        catch (NumberFormatException e ){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        //Hvis ID'erne med succes er blevet hentet, tjekker metoden om nogle af dem er -1. Hvis det er tilfældet, omdirigeres anmodningen til "index"-siden ved hjælp af "response.sendRedirect()". Dette skyldes, at der kræves en gyldig værdi for både top og bund for at tilføje en cupcake til ordren.
        if(bottomID == -1 || topID == -1 ){
            response.sendRedirect("index");
        }

        //Derefter forsøger metoden at hente objekterne "CupcakeBottom" og "CupcakeTop" fra databasen ved hjælp af deres ID'er og "CupcakeBottomFacade" og "CupcakeTopFacade" klasserne. Hvis der opstår en "DatabaseException",
        // sættes en fejlmeddelelse på anmodningen og anmodningen omdirigeres til en "error.jsp" side ved hjælp af "getRequestDispatcher().forward()".
        CupcakeBottom cupcakeBottom = null;
        CupcakeTop cupcakeTop = null;
        try {
            cupcakeBottom = CupcakeBottomFacade.getButtomFromId(bottomID, connectionPool);
            cupcakeTop = CupcakeTopFacade.getTopFromId(topID, connectionPool);
        }
        catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        //Metoden beregner derefter den samlede pris på cupcaken ved at tilføje priserne på bunden og toppen.
        // Dette gøres ved at kalde "getPrice()" på henholdsvis "cupcakeTop" og "cupcakeBottom" objekterne.
        int totalPrice = cupcakeTop.getPrice() + cupcakeBottom.getPrice();

        //Derefter henter metoden den nuværende "Order" fra "HttpSession" ved hjælp af "getAttribute()". Hvis der ikke er nogen ordre, oprettes en ny "Order" ved hjælp af dens standard konstruktør.
        // Derefter oprettes en "CupcakeOrder" objekt, som repræsenterer den cupcake, der skal tilføjes til ordren.
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("current_order");
        CupcakeOrder cupcakeOrder = new CupcakeOrder(topID, bottomID, totalPrice);
        order.addCupcake(cupcakeOrder);
        response.sendRedirect("index");
    }

    //Til sidst tilføjes "CupcakeOrder" objektet til den nuværende "Order", og anmodningen omdirigeres til "index"-siden ved hjælp af "response.sendRedirect()". Dette betyder, at ordren er blevet opdateret med den tilføjede cupcake,
    // og brugeren kan nu fortsætte med at tilføje flere cupcakes til ordren eller gå videre til kassen.
}