package com.szelev.bajnoksag.logic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.data.DataContainer;
import com.szelev.bajnoksag.data.MerkozesEredmeny;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.data.Scores;
import com.szelev.bajnoksag.util.DrawTable;

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
        int numOfTeams = DataContainer.numOfTeams();

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
        Scores.eredmenyek = eredmenyek;
    }

    public void setEredmeny(int i, int j, int egyik, int masik)
    {
        Scores.eredmenyek.get(i).get(j).setEredmeny(egyik, masik);
    }
    
}
