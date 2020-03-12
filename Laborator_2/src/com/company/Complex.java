package com.company;

public class Complex {
    private double re , im;

    public Complex(double r , double img)
    {
        re = r;
        im = img;
    }

    public Complex()
    {
        this(0 , 0);
    }

    public Complex add(Complex num)
    {
        Complex res = new Complex();
        res.re = re + num.re;
        res.im = im + num.im;
        return res;
    }

    public Boolean equals(Complex num)
    {
        if(re == num.re && im == num.im) return true;
        else return false;
    }

    public String toString()
    {
        String result = new String();
        result += "Re = " + re + " Im = " + im;
        return result;
    }

    public static void main(String []args)
    {
        Complex c1 = new Complex(2 , 4);
        Complex c2 = new Complex(2 , 4.3);
        Complex c3 = new Complex(2 , 4);
        System.out.println("C1 == C2? " + c1.equals(c2));
        System.out.println("C1 == C3? " + c1.equals(c3));
        System.out.println(c1.add(c2).toString());
        System.out.println(c1.add(c2).add(c3).toString());
    }


}
