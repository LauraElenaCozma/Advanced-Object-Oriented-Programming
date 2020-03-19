package exercise_4;

public class Main {
    public static void main(String args[])
    {
        FoodProduct f = new FoodProduct(7 , "iaurt" , "lactate");
        ClotheProduct c = new ClotheProduct(110 , "geaca" , "bej");
        FurnitureProduct fur = new FurnitureProduct(2000 , "dulap" , 300 , 100 , "oak");
        CleaningProduct clean = new CleaningProduct(20 , "Pronto" , "mobila");

        Product prod[] = new Product[]{f , c , fur , clean};
        for(Product p : prod)
        {
            System.out.println(p.typeOfProduct());
            System.out.println(p.toString());
        }
        for(Product p : prod)
        {
            p.priceOffer();
            System.out.println(p.toString());
        }


    }
}
