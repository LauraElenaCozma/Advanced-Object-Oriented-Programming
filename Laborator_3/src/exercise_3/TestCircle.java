package exercise_3;

public class TestCircle {
    public static void main(String args[])
    {
        Circle c1 = new Circle();
        Circle c2 = new Circle(2.5);
        Circle c3 = new Circle(4 , "Green");
        Circle c4 = new Circle(2.5 , "Red");
        //equals
        System.out.println(c3.equals(c4));
        System.out.println(c2.equals(c4));
        System.out.println(c3.equals("Red"));
        //get and set
        System.out.println(c1.getRadius());
        c2.setColour("Blue");
        c2.setRadius(10.2);
        //getArea
        System.out.println("Area of c2:" + c2.getArea());
        //toString
        System.out.println(c2.toString());
    }
}
