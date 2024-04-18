package dat.backend.model.entities;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.CupcakeBottomFacade;
import dat.backend.model.persistence.CupcakeTopFacade;

public class CupcakeOrder {
    private int cupcakeOrderId;
    private int orderId;
    private int price;
    private int cupcakeTopId;
    private int cupcakeBottomId;
    private String cupcakeTopFlavor;
    private String cupcakeBottomFlavor;

    public CupcakeOrder(int cupcakeOrderId, int orderId, int price, int cupcakeTopId, int cupcakeBottomId) {
        this.cupcakeOrderId = cupcakeOrderId;
        this.orderId = orderId;
        this.price = price;
        this.cupcakeTopId = cupcakeTopId;
        this.cupcakeBottomId = cupcakeBottomId;
    }

    public CupcakeOrder(int topID, int bottomID, int topPrice, int bottomPrice) {
        cupcakeTopId = topID;
        cupcakeBottomId = bottomID;
        price = topPrice + bottomPrice;
    }

    public CupcakeOrder(int topID, int bottomID, int totalPrice) {
        cupcakeTopId = topID;
        cupcakeBottomId = bottomID;
        price = totalPrice;
    }

    public int getCupcakeOrderId() {
        return cupcakeOrderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCupcakeTopId() {
        return cupcakeTopId;
    }

    public int getCupcakeBottomId() {
        return cupcakeBottomId;
    }

    public int getPrice() {
        return price;
    }

    public void setCupcakeTop(int id) {
        cupcakeTopId = id;
    }

    public void setCupcakeBottom(int id) {
        cupcakeBottomId = id;
    }

    //Disse to metoder er ansvarlige for at hente kage-top- og bundsmag fra deres respektive databaser, hvis de ikke allerede er blevet hentet.


    //Hvis variablen "cupcakeTopFlavor" eller "cupcakeBottomFlavor" er null, indikerer det, at smagen endnu ikke er blevet hentet fra databasen.
    // Hvis variablen ikke er null, vil metoden returnere smagen uden at hente den igen.
    //I begge metoder opretter de først en forbindelse ("connectionPool") til databasen ved at kalde "ApplicationStart.getConnectionPool()".
    // Dette er en statisk metode, der henter en forbindelsespool fra en anden klasse kaldet "ApplicationStart".
    //Derefter kalder de den respektive metode ("CupcakeTopFacade.getTopFlavorFromId()" eller "CupcakeBottomFacade.getBottomFlavorFromId()"), som henter kage-top- eller bundsmagen fra databasen baseret på ID'et for den aktuelle kage.
    // De sender også forbindelsespoolen ("connectionPool") som parameter, så de kan bruge den til at oprette en forbindelse til databasen.
    //Når smagen er hentet fra databasen, gemmer de den i den respektive variabel ("cupcakeTopFlavor" eller "cupcakeBottomFlavor") og returnerer smagen.
    public String getCupcakeTopFlavor() throws DatabaseException {
        if (cupcakeTopFlavor == null) {
            ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
            cupcakeTopFlavor = CupcakeTopFacade.getTopFlavorFromId(cupcakeTopId, connectionPool);
        }

        return cupcakeTopFlavor;
    }

    public String getCupcakeBottomFlavor() throws DatabaseException {
        if (cupcakeBottomFlavor == null) {
            ConnectionPool connectionPool = ApplicationStart.getConnectionPool();
            cupcakeBottomFlavor = CupcakeBottomFacade.getBottomFlavorFromId(cupcakeBottomId, connectionPool);
        }

        return cupcakeBottomFlavor;
    }


}
