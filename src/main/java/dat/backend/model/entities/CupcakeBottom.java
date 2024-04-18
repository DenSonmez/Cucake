package dat.backend.model.entities;

public class CupcakeBottom {
    private int cupcakeBottomId;
    private int price;
    private String flavor;

    public CupcakeBottom(int cupcakeBottomId, int price, String flavor) {
        this.cupcakeBottomId = cupcakeBottomId;
        this.price = price;
        this.flavor = flavor;
    }

    public int getCupcakeBottomId() {
        return cupcakeBottomId;
    }

    public int getPrice() {
        return price;
    }

    public String getFlavor() {
        return flavor;
    }
}
