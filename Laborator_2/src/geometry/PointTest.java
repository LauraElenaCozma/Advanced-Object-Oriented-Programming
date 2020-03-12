package geometry;

public class PointTest {

    public static void main(String []args)
    {
        Point A = new Point(1 , 3);
        Point B = new Point(-1 , 2);
        System.out.println(A.distance(B));
        System.out.println(A.distance(4 , 12));
        A.setX(3);
        System.out.println(A.toString());
        //A.x = 4; can't access, data in private
    }
}
