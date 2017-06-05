package com.szelev.bajnoksag.data;

import java.util.ArrayList;

/**
 * Created by szucs on 2017.03.29..
 */

public class Scores{

    public static ArrayList<ArrayList<MerkozesEredmeny>> scores = null;

    public static String tooString()
    {
        String s = "";

        s += scores.size() + " ";
        s += scores.get(0).size() + " ";
        for(int i=0; i<scores.size(); i++)
        {
            for(int j=0; j<scores.get(0).size(); j++)
            {
                s += getResult(i, j).toString() + " ";
            }
        }
        return s;
    }

    public static void loadFromString(String s)
    {
        String[] splitted = s.trim().split("\\s+");
        int a, b;

        a = Integer.parseInt(splitted[0]);
        b = Integer.parseInt(splitted[1]);

        ArrayList<ArrayList<MerkozesEredmeny>> ns = new ArrayList<>();

        for(int i=0; i<a; i++)
        {
            ArrayList<MerkozesEredmeny> al = new ArrayList<>();
            ns.add(al);
            for(int j=0; j<b; j++)
            {
                MerkozesEredmeny me = new MerkozesEredmeny();
                me.loadFromString(splitted[i*b+j+2]);
                ns.get(i).add(me);
            }
        }
        scores = ns;
    }

    public static void setScores(ArrayList<ArrayList<MerkozesEredmeny>> newScores) {
        if (scores == null) {
            scores = newScores;
        }
    }

    public static MerkozesEredmeny getResult(int firstTeamID, int secondTeamID) {
        return scores.get(firstTeamID).get(secondTeamID);
    }

    public static int getScoreFirstTeam(int firstTeamID, int secondTeamID) {
        return getResult(firstTeamID, secondTeamID).getElso();
    }

    public static int getScoreSecondTeam(int firstTeamID, int secondTeamID) {
        return getResult(firstTeamID, secondTeamID).getMasodik();
    }

    public static void setScore(int firstTeamID, int secondTeamID, int firstScore, int secondScore) {
        getResult(firstTeamID, secondTeamID).setEredmeny(firstScore, secondScore);
    }

}

