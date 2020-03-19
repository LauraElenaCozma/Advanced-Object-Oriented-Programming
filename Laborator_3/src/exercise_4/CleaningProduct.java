package exercise_4;

public class CleaningProduct extends Product{
    private String category;

    CleaningProduct()
    {
        super();
        this.category = "";
    }
    CleaningProduct(String category)
    {
        super();
        this.category = category;
    }

    CleaningProduct(double price , String name , String category)
    {
        super(price , name);
        this.category = category;
    }
    @Override
    public String typeOfProduct()
    {
        return "Cleaning Product";
    }

    @Override
    public void priceOffer()
    {
        price = price * 0.90;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Category: " + category;
    }


}
