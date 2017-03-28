package com.szelev.bajnoksag.data;

/**
 * Created by Levente on 2016.12.16..
 */

public class MerkozesEredmeny {

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
