package exercise_4;

public class FoodProduct extends Product{
    private String category;

    FoodProduct()
    {
        super();
        this.category = "";
    }

    FoodProduct(double price , String name , String category)
    {
        super(price , name);
        this.category = category;
    }

    @Override
    public String typeOfProduct()
    {
        return "Food Product";
    }
    @Override
    public void priceOffer()
    {
        price = price * 0.85;
    }
    @Override
    public String toString()
    {
        return super.toString() + " Category: " + category;
    }
}
