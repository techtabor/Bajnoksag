package com.szelev.bajnoksag.data;


/**
 * Created by Levente on 2016.12.16..
 */

public class MerkozesEredmeny{

    private int     elso, masodik;
    private boolean voltMeccs;

    public MerkozesEredmeny(int elso, int masodik)
    {
        this.elso       = elso;
        this.masodik    = masodik;
        voltMeccs       = true;
    }

    public MerkozesEredmeny()
    {
        voltMeccs = false;
    }

    public String toString()
    {
        String s;
        if(!voltMeccs)
            s = "-1";
        else
            s = Integer.toString((elso+masodik)*(elso+masodik+1)/2+elso);
        return s;
    }

    public void loadFromString(String s)
    {
        int num = Integer.parseInt(s);

        if(num == -1)
        {
            voltMeccs = false;
        }
        else
        {
            voltMeccs = true;
            int n;
            for(n=0; n*(n+1)/2<=num; n++)
            {

            }
            n--;
            elso = num-n*(n+1)/2;
            masodik = n-elso;
        }
    }

    public void setEredmeny(int elso, int masodik)
    {
        this.elso    = elso;
        this.masodik = masodik;
        voltMeccs = true;
    }

    public int getElso()
    {
        return elso;
    }

    public int getMasodik()
    {
        return masodik;
    }

    public boolean voltMeccs()
    {
        return voltMeccs;
    }

}
