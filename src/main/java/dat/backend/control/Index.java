package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.CupcakeBottom;
import dat.backend.model.entities.CupcakeTop;
import dat.backend.model.entities.Order;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "index", urlPatterns = {"/index"})
public class Index extends HttpServlet {

    private ConnectionPool connectionPool;
    private static Order currentOrder;

//init() metoden kaldes når servletten initialiseres, her sætter den ConnectionPool-variablen til værdien fra ApplicationStart-klassen, og initialiserer også "currentOrder" som en tom ordre.
    @Override
    public void init() throws ServletException
    {
        // Henter en ConnectionPool-objekt fra ApplicationStart-klassen.
        this.connectionPool = ApplicationStart.getConnectionPool();
        currentOrder = new Order();
        // Initialiserer "currentOrder" som en ny Order-objekt.
        //
        //Samlet set opretter denne klasse og initialiserer en ConnectionPool og en "currentOrder" variabel, som bruges i andre servlet-klasser.
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Metoden opretter to lister: allTops og allBottoms og initialiserer dem til tomme lister. Disse lister skal indeholde alle CupcakeTop og CupcakeBottom objekter i systemet,
// og de vil blive overført til brugergrænsefladen som en del af HTTP-sessionen.
        List<CupcakeTop> allTops = new ArrayList<>();
        List<CupcakeBottom> allBottoms = new ArrayList<>();


        //Dernæst prøver metoden at hente alle CupcakeTop og CupcakeBottom objekter fra databasen ved hjælp af CupcakeTopFacade og CupcakeBottomFacade klasserne. Hvis der opstår en DatabaseException,
        // bliver en fejlbesked sendt til en fejlside, og metoden returnerer.
        try {
            allTops = CupcakeTopFacade.getAllTops(connectionPool);
            allBottoms = CupcakeBottomFacade.getAllBottoms(connectionPool);
        } catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
//Derefter oprettes en HttpSession, og de hentede CupcakeTop og CupcakeBottom objekter tilføjes til sessionen sammen med den nuværende ordre (som er en statisk variabel i klassen).
// Brugergrænsefladen sendes derefter til index.jsp-siden.
        HttpSession session = request.getSession();
        session.setAttribute("cupcaketops", allTops);
        session.setAttribute("cupcakebottoms", allBottoms);
        setOrder(currentOrder, session);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
//Alt i alt sørger denne metode for at indlæse de nødvendige CupcakeTop og CupcakeBottom objekter og den aktuelle ordre i HTTP-sessionen for at kunne give brugerne mulighed for at bestille cupcakes.

//Koden har en doPost-metode, der håndterer POST-forespørgsler til servletten.
// Denne metode modtager forespørgsler omkring valg af en bund og en top til en cupcake.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//Først tjekker metoden om der ikke er valgt en bund og en top. Hvis dette er tilfældet, videresender den forespørgslen tilbage til forsiden (index.jsp).
        String bottomParameter = request.getParameter("bottom");
        String topParameter = request.getParameter("top");
        if(bottomParameter == null && topParameter == null){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

//Herefter tjekker metoden, om der er valgt en bund. Hvis ikke, videresender den igen forespørgslen tilbage til forsiden. Hvis der er valgt en bund,
// henter metoden bunden fra databasen ved hjælp af bundens id og CupcakeBottomFacade-klassen.
        if(bottomParameter == null){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        int bottomId = Integer.parseInt(bottomParameter);
        CupcakeBottom bottom = null;
        try {
            bottom = CupcakeBottomFacade.getButtomFromId(bottomId, connectionPool);
        } catch (DatabaseException e)
        {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("cupcake_bottom", bottom);


        //Dernæst tjekker metoden, om der er valgt en top. Hvis ikke, videresender den igen forespørgslen tilbage til forsiden. Hvis der er valgt en top, henter metoden toppen fra databasen ved hjælp af toppens id og CupcakeTopFacade-klassen.
        if(topParameter == null){
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        int topId = Integer.parseInt(topParameter);


    }
//Til sidst sætter metoden order-objektet i sessionen, således at det kan tilgås af andre servlets eller JSP-filer.
// Dette gøres ved at kalde setOrder-metoden, som sætter det nuværende order-objekt og derefter sætter sessionattributtet "current_order" til at være det nye order-objekt.
    public static void setOrder(Order order, HttpSession session){
        currentOrder = order;
        session.setAttribute("current_order", currentOrder);
    }

}
