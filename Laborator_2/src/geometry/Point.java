package geometry;
import java.lang.Math;
public class Point {
    private int x , y;
    public Point(int valX , int valY)
    {
        x = valX;
        y = valY;
    }

    public Point()
    {
        this(0 , 0);
    }
    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public String toString()
    {
        return "(" + x +" , " + y + ")";
    }
    double distance(Point p1)
    {
        return Math.sqrt((x - p1.x) * (x - p1.x) + (y - p1.y) * (y - p1.y));
    }
    double distance(int x1 , int x2)
    {
        Point p = new Point(x1 , x2);
        return distance(p);
    }
}
