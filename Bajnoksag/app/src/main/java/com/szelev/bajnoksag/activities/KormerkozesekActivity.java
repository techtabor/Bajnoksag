package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.szelev.bajnoksag.data.DataContainer;
import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.logic.Kormerkozesek;
import com.szelev.bajnoksag.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class KormerkozesekActivity extends AppCompatActivity{

    public static Kormerkozesek kormerkozes;
    private static boolean          vanLogika = false;

    private TableLayout     merkozesTabla;
    private Spinner         csapatok1, csapatok2;
    private TextView scoreOfTeam1TextView, scoreOfTeam2TextView;

    public KormerkozesekActivity() {
        if(!vanLogika)
        {
            kormerkozes = new Kormerkozesek();
            vanLogika = true;
        }
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

    private void refreshTable()
    {
        merkozesTabla.removeAllViews();

        kormerkozes.tablaRajzol(merkozesTabla, this);
    }

    private void initTable()
    {
        refreshTable();

        scoreOfTeam1TextView.setText("0");
        scoreOfTeam2TextView.setText("0");

        List<String> list = new ArrayList<String>();
        list.add("");

        for(int i = 0; i< DataContainer.numOfTeams(); i++)
        {
            list.add(DataContainer.getTeam(i).getNev());
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

        int scoreOfFirstTeam = Integer.parseInt(scoreOfTeam1TextView.getText().toString());
        int scoreOfSecondTeam = Integer.parseInt(scoreOfTeam2TextView.getText().toString());

        if(i!=j && i!=-1 && j!=-1) {
            kormerkozes.setEredmeny(i, j, scoreOfFirstTeam, scoreOfSecondTeam);
            kormerkozes.setEredmeny(j, i, scoreOfSecondTeam, scoreOfFirstTeam);
        }

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

}