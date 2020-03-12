package com.company;

import java.util.Arrays;

public class Coada {
    private int []v;
    private int nrElem;

    public Coada()
    {
        nrElem = 100;
        v = new int[nrElem];
    }

    public Coada(int dim)
    {
        nrElem = dim;
        v = new int[nrElem];
    }

    public boolean isEmpty()
    {
        if(nrElem != 0)return false;
        return true;
    }


    public String toString()
    {
        String res = new String();
        res += "Dimensiunea: " + nrElem + " Elementele: " + Arrays.toString(v);
        return res;
    }

    public void push(int val)
    {
        int w[] = Arrays.copyOf(v , nrElem + 1);
        w[nrElem] = val;
        nrElem ++;
        v = Arrays.copyOf(w , nrElem);
    }

    public int pop()
    {
        int w[] = Arrays.copyOf(v , nrElem);
        nrElem --;
        v = new int[nrElem];
        v = Arrays.copyOfRange(w , 1 , w.length);
        return w[0];
    }

    public static void main(String args[])
    {
        Coada q = new Coada(3);
        System.out.println(q.toString());
        q.push(4);
        q.push(22);
        System.out.println(q.pop());
        q.push(3);
        System.out.println(q.toString());
        q.push(6);
        System.out.println(q.pop());
        System.out.println(q.toString());
        System.out.println(q.pop());
        q.push(12);
        System.out.println(q.toString());
        System.out.println(q.isEmpty());
    }


}

