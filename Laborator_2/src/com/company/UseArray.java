package com.company;
import java.util.Arrays;
public class UseArray {
    public static int[] searchCopy(int array[] , int elem)
    {
        int index = array.length;
        for(int i = 0 ; i < array.length ; i++)
        {
            if(array[i] == elem)
            {
                index = i;
                break;
            }
        }
        System.out.println(index);
        return Arrays.copyOfRange(array , index , array.length);
    }

    public static void main(String args[])
    {
        int arr[] = {1 , 5 , 6 , 9 , 12 , 22 , 7 , 20};
        int item = 9;
        int result[] = searchCopy(arr , item);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(result));
    }
}
