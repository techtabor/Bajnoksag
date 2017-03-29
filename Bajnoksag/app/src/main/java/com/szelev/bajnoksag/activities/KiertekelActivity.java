package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.CsapatTul;
import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.data.Scores;
import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Levente on 2016.12.18..
 */


public class KiertekelActivity extends AppCompatActivity{

    private TableLayout                 ertekeloTabla;
    public  static ArrayList<CsapatTul>   sorrend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiertekel);

        sorrendKiszamol();

        init();
    }

    public void init() {
        ertekeloTabla   = (TableLayout) (findViewById(R.id.table_main));

        kiirErtekeles();
    }

    private void kiirErtekeles() {
        ertekeloTabla.addView(DrawTable.createRowWithTwoCell("     Helyezés     ", "     Csapatnév     ", this));

        for(int i = 0; i < sorrend.size(); i++)
        {
            String teamName = Teams.getTeam(sorrend.get(i).ID).getNev();
            TableRow newRow = DrawTable.createRowWithTwoCell(Integer.toString(i+1), teamName, this);

            ertekeloTabla.addView(newRow);
        }
    }

    public static void sorrendKiszamol()
    {
        sorrend = new ArrayList<>();

        // kezdetben a csapatok index szerint vannak sorbarendezve

        ArrayList<Csapat> teams = Teams.getTeams();
        for(int i = 0; i < teams.size(); i++)
        {
            CsapatTul ct = new CsapatTul();
            ct.ID = i;
            sorrend.add(ct);
        }
        //sorbarakPontszam(0, DrawTable.csapatok.size()-1);

        for(int i = 0; i< teams.size(); i++)
        {
            for(int j = 0; j< teams.size(); j++)
            {
                if(i!=j)
                {
                    // TODO (szgabbor) Logikátlan, hogy a nem lejátszott meccsnál is van pontszámítás, ezt  át kellene írni.
                    boolean alreadyPlayedTheMatch = Scores.getResult(i, j).voltMeccs();
                    int firstTeamScore = Scores.getScoreFirstTeam(i, j);
                    int secondTeamScore = Scores.getScoreSecondTeam(i, j);

                    CsapatTul currentTeam = sorrend.get(i);

                    currentTeam.pontszam += getPontszam(firstTeamScore, secondTeamScore, alreadyPlayedTheMatch);
                    currentTeam.pontkul  += firstTeamScore - secondTeamScore;
                    currentTeam.szerzettPont += firstTeamScore;
                    if(firstTeamScore > secondTeamScore)
                        currentTeam.gyozelemszam++;
                    if(alreadyPlayedTheMatch)
                        currentTeam.jatszottMeccsek++;
                }
            }
        }

        Collections.sort(sorrend, new Comparator<CsapatTul>() {
            @Override
            public int compare(CsapatTul o1, CsapatTul o2) {
                if(((Integer)(o1.pontszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontszam*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.pontszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontszam*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.pontkul*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontkul*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.pontkul*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontkul*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.szerzettPont*o2.jatszottMeccsek)).compareTo((Integer)(o2.szerzettPont*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.szerzettPont*o2.jatszottMeccsek)).compareTo((Integer)(o2.szerzettPont*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.gyozelemszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.gyozelemszam*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.gyozelemszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.gyozelemszam*o1.jatszottMeccsek)))*(-1);
                }
                return 0;
            }
        });


    }

    private static int getPontszam(int egyik, int masik, boolean voltMeccs)
    {
        if(!voltMeccs)
            return KiertekelesBeallitasokActivity.nemVoltMegMeccsPont;
        if(egyik > masik)
            return KiertekelesBeallitasokActivity.gyozelemPont;
        if(egyik < masik)
            return KiertekelesBeallitasokActivity.veresegPont;
        return KiertekelesBeallitasokActivity.dontetlenPont;
    }

    //onClick event
    public void actionOnVisszaButton(View v)
    {
        CreateActivity.start(KormerkozesekActivity.class, this);
    }

    //onClick event
    public void actionOnTovabbButton(View v)
    {
        CreateActivity.start(KiertekelesBeallitasokActivity.class, this);
    }

}