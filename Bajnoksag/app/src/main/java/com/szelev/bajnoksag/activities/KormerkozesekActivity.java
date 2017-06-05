package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.data.Scores;
import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.logic.Kormerkozesek;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.util.SaveLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class KormerkozesekActivity extends AppCompatActivity{
    public static Kormerkozesek kormerkozes;

    private TableLayout     merkozesTabla;
    private Spinner         csapatok1, csapatok2;
    private TextView scoreOfTeam1TextView, scoreOfTeam2TextView;

    public KormerkozesekActivity() {
        kormerkozes = new Kormerkozesek();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kormerkozesek);

        init();
    }

    public void init()
    {
        merkozesTabla   = (TableLayout) (findViewById(R.id.table_main));
        csapatok1       = (Spinner)     (findViewById(R.id.spinner1));
        csapatok2       = (Spinner)     (findViewById(R.id.spinner2));
        scoreOfTeam1TextView = (TextView)    (findViewById(R.id.editText));
        scoreOfTeam2TextView = (TextView)    (findViewById(R.id.editText2));

        initTable();
    }

    private void refreshTable() {
        merkozesTabla.removeAllViews();

        tablaRajzol(merkozesTabla, this);
    }

    private void initTable()
    {
        refreshTable();

        scoreOfTeam1TextView.setText("0");
        scoreOfTeam2TextView.setText("0");

        List<String> list = new ArrayList<String>();
        list.add("");

        for(int i = 0; i< Teams.numOfTeams(); i++)
        {
            list.add(Teams.getTeam(i).getNev());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        csapatok1.setAdapter(dataAdapter);
        csapatok2.setAdapter(dataAdapter);
    }

    //onClick action
    public void actionOnMentesButton(View v) {
        int i, j;

        i = (int) csapatok1.getSelectedItemId()-1;
        j = (int) csapatok2.getSelectedItemId()-1;

        csapatok1.setSelection(0);
        csapatok2.setSelection(0);

        String scoreOfTeam1 = scoreOfTeam1TextView.getText().toString();
        String scoreOfTeam2 = scoreOfTeam2TextView.getText().toString();

        kormerkozes.saveScores(i, j, scoreOfTeam1, scoreOfTeam2);

        refreshTable();

        scoreOfTeam1TextView.setText("0");
        scoreOfTeam2TextView.setText("0");
    }

    private void setScoresAndSave(String firstScore, String secondScore, View v) {
        scoreOfTeam1TextView.setText(firstScore);
        scoreOfTeam2TextView.setText(secondScore);

        actionOnMentesButton(v);
    }

    //onClilck action
    public void actionOnSaveButton(View v)
    {
        SaveLoad.saveKorm("korm.bnk", getApplicationContext());
    }

    //onClilck action
    public void actionOnLoadButton(View v)
    {
        SaveLoad.loadKorm("korm.bnk", getApplicationContext());
        init();
    }

    //onClilck action
    public void actionOnEredmeny1Button(View v)
    {
        setScoresAndSave("1", "0", v);
    }

    //onClilck action
    public void actionOnEredmeny2Button(View v)
    {
        setScoresAndSave("0", "1", v);
    }

    //onClilck action
    public void actionOnEredmeny3Button(View v)
    {
        setScoresAndSave("1", "1", v);
    }

    //onClilck action
    public void actionOnEredmeny4Button(View v)
    {
        setScoresAndSave("2", "2", v);
    }

    //onClick action
    public void actionOnTovabbButton(View v)
    {
        CreateActivity.start(KiertekelActivity.class, this);
    }

    public void tablaRajzol(TableLayout tabl, final AppCompatActivity compatActivity)
    {
        TableRow tr = new TableRow(compatActivity);

        TextView tv;

        tv = DrawTable.createTextView("", compatActivity);
        tr.addView(tv);
        tv = DrawTable.createTextView("", compatActivity);
        tr.addView(tv);
        int numOfTeams = Teams.numOfTeams();

        for(int i = 0; i < numOfTeams; i++)
        {
            tv = DrawTable.createTextView(" " + (i+1) + " ", compatActivity, 120);
            tr.addView(tv);
        }
        tabl.addView(tr);

        for(int i = 0; i< numOfTeams; i++)
        {
            tr = new TableRow(compatActivity);
            tv = DrawTable.createTextView(" " + (i+1) + " ", compatActivity, 30);
            tr.addView(tv);
            tv = DrawTable.createTextView(" " + Teams.getTeam(i).getNev() + " ", compatActivity);
            tr.addView(tv);

            for(int j = 0; j< numOfTeams; j++)
            {
                if(Scores.getResult(i, j).voltMeccs()) {
                    String scoresString = " " + Scores.getScoreFirstTeam(i, j) +
                            ":" + Scores.getScoreSecondTeam(i, j) + " ";
                    tv = DrawTable.createTextView(scoresString, compatActivity);
                }
                else
                {
                    tv = DrawTable.createTextView(" - ", compatActivity);
                }
                final int finalI = i;
                final int finalJ = j;
                tv.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Spinner sp1 = (Spinner)     (compatActivity.findViewById(R.id.spinner1));
                                Spinner sp2 = (Spinner)     (compatActivity.findViewById(R.id.spinner2));

                                sp1.setSelection(finalI+1);
                                sp2.setSelection(finalJ+1);
                            }
                        }
                );
                tr.addView(tv);
            }

            tabl.addView(tr);
        }
    }

}
