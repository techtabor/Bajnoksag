package com.szelev.bajnoksag.data;

import com.szelev.bajnoksag.saveable;

/**
 * Created by Levente on 2016.12.16..
 */

public class MerkozesEredmeny implements saveable{

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
        s = Integer.toString(elso) + " " + Integer.toString(masodik) + " " + Boolean.toString(voltMeccs) + "\n";
        return s;
    }

    public void loadFromString(String s)
    {
        String[] splitted = s.trim().split("\\s+");
        elso = Integer.parseInt(splitted[0]);
        masodik = Integer.parseInt(splitted[1]);
        voltMeccs = Boolean.parseBoolean(splitted[2]);
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
