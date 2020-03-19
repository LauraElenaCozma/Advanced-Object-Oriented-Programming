package exercise_4;

public class ClotheProduct extends Product{
    private String colour;

    ClotheProduct()
    {
        colour = "";
    }

    ClotheProduct(String colour)
    {
        super();
        this.colour = colour;
    }

    ClotheProduct(double price , String name , String colour)
    {
        super(price , name);
        this.colour = colour;
    }

    @Override
    public String typeOfProduct()
    {
        return "Clothe Product";
    }

    @Override
    public void priceOffer()
    {
        price = price * 1.20;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Colour: " + colour;
    }
}
