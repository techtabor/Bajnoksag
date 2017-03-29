package com.szelev.bajnoksag.logic;

import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.data.MerkozesEredmeny;
import com.szelev.bajnoksag.data.Scores;

import java.util.ArrayList;

/**
 * Created by Levente on 2017.01.24..
 */

public class Kormerkozesek {

    public Kormerkozesek() {
        initEredmenyek();
    }

    private void initEredmenyek() {
        ArrayList<ArrayList<MerkozesEredmeny>> eredmenyek = new ArrayList<>();
        int numOfTeams = Teams.numOfTeams();

        for(int i=0; i<numOfTeams; i++)
        {
            ArrayList<MerkozesEredmeny> merkEr = new ArrayList<>();
            for(int j=0; j<numOfTeams; j++)
            {
                MerkozesEredmeny merk = new MerkozesEredmeny();
                merkEr.add(merk);
            }
            eredmenyek.add(merkEr);
        }
        Scores.setScore(eredmenyek);
    }

    public void saveScores(int team1ID, int team2ID, String scoreOfTeam1, String scoreOfTeam2) {
        int scoreOfFirstTeam = Integer.parseInt(scoreOfTeam1);
        int scoreOfSecondTeam = Integer.parseInt(scoreOfTeam2);

        if(team1ID != team2ID && team1ID !=-1 && team2ID != -1) {
            Scores.setScore(team1ID, team2ID, scoreOfFirstTeam, scoreOfSecondTeam);
            Scores.setScore(team2ID, team1ID, scoreOfSecondTeam, scoreOfFirstTeam);
        }
    }

}
