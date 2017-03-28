package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableRow;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.DataContainer;
import com.szelev.bajnoksag.util.DrawTable;

public class CsapatHozzaadasL {

    public CsapatHozzaadasL()
    {

    }

    public void addCsapat(int ID, String nev)
    {
        Csapat c = new Csapat(ID, nev);
        DataContainer.getTeams().add(c);
    }

    public void addCsapat(String nev)
    {
        addCsapat(DataContainer.numOfTeams(), nev);
    }

    public TableRow getCsapatRowByIndex(int index, AppCompatActivity act)
    {
        String s1, s2, s3;
        int numOfTeams = DataContainer.numOfTeams();
        s1 = DataContainer.getTeam(index*3).getNev();
        if(numOfTeams > index*3+1)
        {
            s2 = DataContainer.getTeam(index*3+1).getNev();
            if(numOfTeams > index*3+2)
            {
                s3 = DataContainer.getTeam(index*3+2).getNev();
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
