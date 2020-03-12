package Distribution;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product()
    {
        name = "";
        price = 0;
        quantity = 0;
    }
    public Product(String n , double pr , int q)
    {
        name = n;
        price = pr;
        quantity = q;
    }
    public String toString()
    {
        String result = new String();
        result += "Product: " + name + " Price: " + price + " Quantity: " + quantity;
        return result;
    }
    public double getTotalProduct()
    {
        return price * quantity;
    }
}

class Store{
    private String name;
    private Product Prod[];

    public Store()
    {
        name = "";
        Prod = new Product[3];
    }
    public Store(String n , Product p1 , Product p2 , Product p3)
    {
        name = n;
        Prod = new Product[3];
        Prod[0] = p1;
        Prod[1] = p2;
        Prod[2] = p3;
    }
    public double getTotalStore()
    {
        double s = 0;
        for(int i = 0 ; i < 3 ; i++)
            s += Prod[i].getTotalProduct();
        return s;
    }
    public String toString()
    {
        String res = new String();
        res += "Name: " + name + " Products:\n";
        for(int i = 0 ; i < 3 ; i++)
        {
            res += Prod[i].toString();
            res += "\n";

        }
        return res;
    }
}
public class Main {

    public static void main(String args[])
    {
        Store s = new Store("Ikea" , new Product("Banane" , 3 , 10) , new Product("Ciocolata" , 10 , 2) , new Product("Lenor" , 20 , 5));
        System.out.println(s.toString());
        System.out.println(s.getTotalStore());
    }
}
