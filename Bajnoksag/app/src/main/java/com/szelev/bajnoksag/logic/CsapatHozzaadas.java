package com.szelev.bajnoksag.logic;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableRow;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.util.DrawTable;

public class CsapatHozzaadas {

    public CsapatHozzaadas()
    {

    }

    public void addCsapat(int ID, String nev)
    {
        Csapat c = new Csapat(ID, nev);
        Teams.getTeams().add(c);
    }

    public void addCsapat(String nev)
    {
        addCsapat(Teams.numOfTeams(), nev);
    }

    public TableRow getCsapatRowByIndex(int index, AppCompatActivity act)
    {
        String s1, s2, s3;
        int numOfTeams = Teams.numOfTeams();
        s1 = Teams.getTeam(index*3).getNev();
        if(numOfTeams > index*3+1)
        {
            s2 = Teams.getTeam(index*3+1).getNev();
            if(numOfTeams > index*3+2)
            {
                s3 = Teams.getTeam(index*3+2).getNev();
            }
            else
            {
                s3 = "";
            }
        }
        else
        {
            s2 = "";
            s3 = "";
        }

        return DrawTable.createRowWithThreeCell(" " + s1 + " ", " " + s2 + " ", " " + s3 + " ", act);
    }

}
