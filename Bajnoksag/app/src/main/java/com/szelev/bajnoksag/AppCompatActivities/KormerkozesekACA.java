package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.szelev.bajnoksag.data.DataContainer;
import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.Logic.KormerkozesekL;
import com.szelev.bajnoksag.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class KormerkozesekACA extends AppCompatActivity{

    public static KormerkozesekL    logika;
    private static boolean          vanLogika = false;

    private TableLayout     merkozesTabla;
    private Spinner         csapatok1, csapatok2;
    private TextView        er1, er2;

    public KormerkozesekACA() {
        if(!vanLogika)
        {
            logika = new KormerkozesekL();
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
        er1             = (TextView)    (findViewById(R.id.editText));
        er2             = (TextView)    (findViewById(R.id.editText2));

        initTable();
    }

    //TODO (szgabbor): Mi ez a nagy T?
    //(szlev): Tabla
    private void refreshTable()
    {
        merkozesTabla.removeAllViews();

        logika.tablaRajzol(merkozesTabla, this);
    }

    private void initTable()
    {
        refreshTable();

        er1.setText("0");
        er2.setText("0");

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
    public void actionOnMentesButton(View v)
    {
        int i, j;

        i = (int) csapatok1.getSelectedItemId()-1;
        j = (int) csapatok2.getSelectedItemId()-1;

        csapatok1.setSelection(0);
        csapatok2.setSelection(0);



        if(i!=j && i!=-1 && j!=-1)
        {
            logika.setEredmeny(i, j, Integer.parseInt(er1.getText().toString()), Integer.parseInt(er2.getText().toString()));
            logika.setEredmeny(j, i, Integer.parseInt(er2.getText().toString()), Integer.parseInt(er1.getText().toString()));
        }

        refreshTable();

        er1.setText("0");
        er2.setText("0");
    }

    //onClilck action
    public void actionOnEredmeny1Button(View v)
    {
        er1.setText("1");
        er2.setText("0");

        actionOnMentesButton(v);
    }

    //onClilck action
    public void actionOnEredmeny2Button(View v)
    {
        er1.setText("0");
        er2.setText("1");

        actionOnMentesButton(v);
    }

    //onClilck action
    public void actionOnEredmeny3Button(View v)
    {
        er1.setText("1");
        er2.setText("1");

        actionOnMentesButton(v);
    }

    //onClilck action
    public void actionOnEredmeny4Button(View v)
    {
        er1.setText("2");
        er2.setText("2");

        actionOnMentesButton(v);
    }

    //onClick action
    public void actionOnTovabbButton(View v)
    {
        CreateActivity.start(KiertekelACA.class, this);
    }

}
