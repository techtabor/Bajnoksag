package com.szelev.bajnoksag.data;

import java.util.ArrayList;

/**
 * Created by szucs on 2017.03.29..
 */

public class Scores {
    public static ArrayList<ArrayList<MerkozesEredmeny>> scores = null;
    
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

