package com.szelev.bajnoksag;

/**
 * Created by Levente on 2016.12.06..
 */

public class Csapat {

    private int ID;
    private String nev;
    private int suly;

    public Csapat() {
        ID      = -1;
        nev     = "";
        suly    = 0;
    }

    public Csapat(int ID, String nev, int suly) {
        this.ID     = ID;
        this.nev    = nev;
        this.suly   = suly;
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

    public void setSuly(int suly) {
        this.suly   = suly;
    }

    public void setSuly(String suly) {
        this.suly = Integer.parseInt(suly);
    }

    public int getSuly() {
        return suly;
    }

}
