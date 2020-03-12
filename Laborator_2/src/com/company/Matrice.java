package com.company;
import java.util.Scanner;

public class Matrice {
    private int m[][];
    private int row, col;

    public Matrice()
    {
        row = 0;
        col = 0;
        m = new int[row][col];
    }
    public Matrice(int r , int c)
    {
        row = r;
        col = c;
        m = new int[row][col];
    }
    public void readMatrix()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("No. of rows:");
        row = sc.nextInt(); //read the number of rows
        System.out.println("No. of columns");
        col = sc.nextInt(); //read the number of columns

        m = new int[row][col];
        for(int i = 0 ; i < row ; i++)
            for(int j = 0 ; j < col ; j++)
                m[i][j] = sc.nextInt();
    }
    public String toString()
    {
        String result = new String();
        result += "No. of rows " +  row + " and no. of columns " +  col + '\n';
        for(int i = 0 ; i < row ; i++)
        {
            for(int j = 0 ; j < col ; j++)
                result += m[i][j] + " ";
            result += '\n';
        }
        return result;

    }
    public Matrice add(Matrice m2)
    {
        if(row != m2.row || col != m2.col)
        {
            System.out.println("Can't add, different dimensions");
            return m2;
        }
        else
        {
            Matrice res = new Matrice(row , col);
            for(int i = 0 ; i < row ; i++)
                for(int j = 0 ; j < col ; j++)
                    res.m[i][j] = m[i][j] + m2.m[i][j];

            return res;
        }
    }

    public Matrice multiply(Matrice m2)
    {
        if(col != m2.row)
        {
            System.out.println("Can't multiply, different dimensions");
            return m2;
        }
        else
        {
            Matrice res = new Matrice(row , m2.col);
            for(int i = 0 ; i < row ; i++)
            {
                for(int j = 0 ; j < m2.col ; j++)
                {
                    for(int k = 0 ; k < col ; k++)
                        res.m[i][j] += m[i][k] * m2.m[k][j];
                }

            }

            return res;

        }
    }

    public Matrice pow(int power)
    {
        if(power < 0)
            throw new ArithmeticException("Power should be positive!");
        if(row != col)
            throw new ArithmeticException("Wrong dimensions");
        Matrice res = new Matrice(row , col);
        for(int i = 0 ; i < row ; i++)
            res.m[i][i] = 1;
        for(int i = 0 ; i < power ; i++)
        {
            res = multiply(res);
        }

        return res;
    }

    public static void main(String[] args)
    {
        Matrice m1 = new Matrice();
        Matrice m2 = new Matrice();
        m1.readMatrix();
        m2.readMatrix();
        Matrice m3;

        System.out.println(m1.toString());
        System.out.println(m2.toString());
        m3 = m1.multiply(m2);
        System.out.println("Result of multiply is:\n" + m3.toString());
        m2 = m1.pow(3);
        System.out.println("Result of pow is:\n" + m2.toString());
    }

}
