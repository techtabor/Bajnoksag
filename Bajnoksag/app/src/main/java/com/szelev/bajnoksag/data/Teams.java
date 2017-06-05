package com.szelev.bajnoksag.data;

import java.util.ArrayList;

/**
 * Created by szucs on 2017.03.28..
 */

public class Teams{

    private static ArrayList<Csapat> csapatok = new ArrayList<>();
    private static ArrayList<Csapat> tovabbjutok = new ArrayList<>();

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
