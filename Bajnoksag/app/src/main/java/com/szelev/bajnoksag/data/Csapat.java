package com.szelev.bajnoksag.data;

import com.szelev.bajnoksag.saveable;

/**
 * Created by Levente on 2016.12.06..
 */

public class Csapat implements saveable{

    private int ID;
    private String nev;

    public Csapat() {
        ID      = -1;
        nev     = "";
    }

    public String toString()
    {
        String s;
        s = ID + " " + nev;
        return s;
    }

    public void loadFromString(String s)
    {
        String[] splitted = s.trim().split("\\s+");
        setID(Integer.parseInt(splitted[0]));
        String n = "";
        for(int i=1; i<splitted.length; i++)
        {
            n += splitted[i] + " ";
        }
        setNev(n.trim());
    }

    public Csapat(int ID, String nev) {
        this.ID     = ID;
        this.nev    = nev;
    }

    public void setID(int ID) {
        this.ID     = ID;
    }

    public int getID() {
        return ID;
    }

    public void setNev(String nev) {
        this.nev    = nev;
    }

    public String getNev() {
        return nev;
    }

}
