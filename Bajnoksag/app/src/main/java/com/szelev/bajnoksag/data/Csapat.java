package com.szelev.bajnoksag.data;

/**
 * Created by Levente on 2016.12.06..
 */

public class Csapat {

    private int ID;
    private String nev;

    public Csapat() {
        ID      = -1;
        nev     = "";
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
