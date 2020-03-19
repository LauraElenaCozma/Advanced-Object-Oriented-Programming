package exercise_3;

public class Circle {
    private double radius;
    private String colour;
    public Circle(double r , String c)
    {
        radius = r;
        colour = c;
    }
    public Circle()
    {
        this(0.1 , "Red");
    }
    public Circle(double r)
    {
        this(r , "Red");
    }
    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public double getRadius() {
        return radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
    public String toString()
    {
        return "Radius: " + radius + " Colour: " + colour;
    }
    public Boolean equals(Circle c)
    {
        if(c instanceof Circle && this instanceof Circle)
        {
            if(c.radius == this.radius && c.colour == this.colour)
                return true;
        }
        return false;
    }



}
