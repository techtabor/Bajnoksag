package com.szelev.bajnoksag.data;

/**
 * Created by Levente on 2017.01.30..
 */

public class Merkozes {

    private Csapat cs1, cs2, gyoztes = null;
    private int eredmeny1, eredmeny2;
    private int index;
    private boolean lejatszott = false;
    private int inputId1, inputId2;

    public Merkozes(Csapat c1, Csapat c2, int ind)
    {
        setCs1(c1);
        setCs2(c2);
        setIndex(ind);
    }

    public void lejatszas(int er1, int er2)
    {
        setLejatszott(true);
        setEredmeny1(er1);
        setEredmeny2(er2);
        if(getEredmeny1() > getEredmeny2())
            setGyoztes(getCs1());
        else
            setGyoztes(getCs2());
    }

    public int getInputId1() {
        return inputId1;
    }

    public void setInputId1(int inputId1) {
        this.inputId1 = inputId1;
    }

    public int getInputId2() {
        return inputId2;
    }

    public void setInputId2(int inputId2) {
        this.inputId2 = inputId2;
    }

    public int getEredmeny1() {
        return eredmeny1;
    }

    public void setEredmeny1(int eredmeny1) {
        this.eredmeny1 = eredmeny1;
    }

    public int getEredmeny2() {
        return eredmeny2;
    }

    public void setEredmeny2(int eredmeny2) {
        this.eredmeny2 = eredmeny2;
    }

    public Csapat getGyoztes() {
        return gyoztes;
    }

    public void setGyoztes(Csapat gyoztes) {
        this.gyoztes = gyoztes;
    }

    public boolean isLejatszott() {
        return lejatszott;
    }

    public void setLejatszott(boolean lejatszott) {
        this.lejatszott = lejatszott;
    }

    public Csapat getCs1() {
        return cs1;
    }

    public void setCs1(Csapat cs1) {
        this.cs1 = cs1;
    }

    public Csapat getCs2() {
        return cs2;
    }

    public void setCs2(Csapat cs2) {
        this.cs2 = cs2;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
