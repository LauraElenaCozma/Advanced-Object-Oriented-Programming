package com.company;

public class Maximum {
    private int nr;

    public Maximum()
    {
        nr = 0;
    }
    // Overloading
    public Maximum(int a)
    {
        nr = a;
    }
    public int maxim(int a)
    {
        if(nr >  a)
            return nr;
        return a;
    }
    public int maxim(int a , int b)
    {
        Maximum m = new Maximum(a);
        return maxim(m.maxim(b));
    }
    public int maxim(int a , int b , int c)
    {
        return maxim(a , maxim(b , c));
    }
    public int maxim(int a , int b , int c , int d)
    {
        return maxim(a , b , maxim(c , d));
    }

    public static void main(String[] args)
    {
        Maximum max = new Maximum(10);
        System.out.println(max.maxim(12));
        System.out.println(max.maxim(-1));
        System.out.println(max.maxim(-1 , 4));
        System.out.println(max.maxim(11 , 22));
        System.out.println(max.maxim(4 , 12 ,100));
        System.out.println(max.maxim(4 , 12 ,100 , 23));
        System.out.println(max.maxim(4 , 1 ,0 , 3));
        System.out.println(max.maxim(1 , 14 ,1 , 2));






    }
}
