package exercise_4;

public class FurnitureProduct extends Product {
    private String wood;
    private int height;
    private int width;
    FurnitureProduct()
    {
        height = width = 1;
        wood = "oak";
    }

    FurnitureProduct(int height , int width , String wood)
    {
        super();
        this.height = height;
        this.width = width;
        this.wood = wood;
    }

    FurnitureProduct(double price , String name , int height , int width , String wood)
    {
        super(price , name);
        this.height = height;
        this.width = width;
        this.wood = wood;
    }
    @Override
    public String typeOfProduct()
    {
        return "Furniture Product";
    }

    @Override
    public void priceOffer()
    {
        price = price * 1.15;
    }

    @Override
    public String toString()
    {
        return super.toString() + " Height: " + height + " Width: " + width + " Wood: " + wood;
    }
}
