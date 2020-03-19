package exercise_4;

public abstract class Product {
    protected double price;
    protected String name;
    public Product()
    {
        price = 0.0;
        name = "";
    }

    public Product(double price , String name)
    {
        this.price = price;
        this.name = name;
    }
    public abstract String typeOfProduct();
    public abstract void priceOffer();

    public String toString()
    {
        return "Price: " + price + " Name: " + name;
    }
}
