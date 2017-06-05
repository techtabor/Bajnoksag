package com.szelev.bajnoksag.data;

import com.szelev.bajnoksag.saveable;

/**
 * Created by Levente on 2017.01.30..
 */

public class CsapatTul implements saveable
{
    public int ID;
    public int pontszam = 0;
    public int pontkul = 0;
    public int szerzettPont = 0;
    public int gyozelemszam = 0;
    public int jatszottMeccsek = 0;

    public String toString()
    {
        String s;
        s = Integer.toString(ID) + " " + Integer.toString(pontszam) + " " + Integer.toString(pontkul)
                + " " + Integer.toString(szerzettPont) + " " + Integer.toString(gyozelemszam) + " "
                + Integer.toString(jatszottMeccsek);
        return s;
    }

    @Override
    public void loadFromString(String s) {
        String[] splitted = s.trim().split("\\s+");
        ID = Integer.parseInt(splitted[0]);
        pontszam = Integer.parseInt(splitted[1]);
        pontkul = Integer.parseInt(splitted[2]);
        szerzettPont = Integer.parseInt(splitted[3]);
        gyozelemszam = Integer.parseInt(splitted[4]);
        jatszottMeccsek = Integer.parseInt(splitted[5]);
    }
}
