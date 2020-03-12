package com.company;

public class MatriceSimetrica {
    public static boolean verificareMatriceSimetrica(int m[][] , int dim)
    {
        for(int i = 0 ; i < dim ; i++)  {
            for(int j = 0 ; j < i ; j++)
                if(m[i][j] != m[j][i])
                    return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        int m[][] = new int[][] {{1 , 7 , 3} , {7 , 4 , -5} , {3 , -5 , 6}};
        if(verificareMatriceSimetrica(m , 3))
            System.out.println("Este simetrica");
        else
            System.out.println("Nu este simetrica");
    }

}
