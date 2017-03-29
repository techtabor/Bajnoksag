package com.szelev.bajnoksag.data;

import java.util.ArrayList;

/**
 * Created by szucs on 2017.03.28..
 */

public class Teams {
    public static ArrayList<Csapat> csapatok = new ArrayList<>();

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
