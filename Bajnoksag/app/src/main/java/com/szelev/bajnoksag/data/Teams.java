package com.szelev.bajnoksag.data;

import java.util.ArrayList;

/**
 * Created by szucs on 2017.03.28..
 */

public class Teams{

    private static ArrayList<Csapat> csapatok = new ArrayList<>();
    private static ArrayList<Csapat> tovabbjutok = new ArrayList<>();

    public static String csapatokToString()
    {
        String s = "";
        s += Integer.toString(csapatok.size()) + "\t";
        for(int i=0; i<csapatok.size(); i++)
            s += csapatok.get(i).toString() + "\t";
        return s;
    }

    public static void csapatokLoadFromString(String s)
    {
        String[] splitted = s.trim().split("\\t+");
        int n = Integer.parseInt(splitted[0]);
        csapatok.clear();
        for(int i=0; i<n; i++)
        {
            Csapat c = new Csapat();
            c.loadFromString(splitted[i+1]);
            csapatok.add(c);
        }
    }

    public static ArrayList<Csapat> getTovabbjutok() {
        return tovabbjutok;
    }

    public static Csapat getTovabbjuto(int index) {
        return tovabbjutok.get(index);
    }

    public static int numOfTovabbjuto() {
        return tovabbjutok.size();
    }

    public static ArrayList<Csapat> getTeams() {
        return csapatok;
    }

    public static Csapat getTeam(int index) {
        return csapatok.get(index);
    }

    public static int numOfTeams() {
        return csapatok.size();
    }

}
