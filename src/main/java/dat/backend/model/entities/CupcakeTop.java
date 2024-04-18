package dat.backend.model.entities;

public class CupcakeTop {
    private int cupcakeTopId;
    private int price;
    private String flavor;

    public CupcakeTop(int cupcakeTopId, int price, String flavor) {
        this.cupcakeTopId = cupcakeTopId;
        this.price = price;
        this.flavor = flavor;
    }

    public int getCupcakeTopId() {
        return cupcakeTopId;
    }

    public int getPrice() {
        return price;
    }

    public String getFlavor() {
        return flavor;
    }
}
