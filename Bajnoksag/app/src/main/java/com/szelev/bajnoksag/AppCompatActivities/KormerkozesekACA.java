package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.Logic.KormerkozesekL;
import com.szelev.bajnoksag.MerkozesEredmeny;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class KormerkozesekACA extends AppCompatActivity{

    public  static KormerkozesekL   logika;
    private static boolean          vanLogika = false;

    private TableLayout     merkozesTabla;
    private Spinner         csapatok1, csapatok2;
    private TextView        er1, er2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kormerkozesek);

        init();
    }

    public void init()
    {
        if(!vanLogika)
        {
            logika = new KormerkozesekL();
            vanLogika = true;
        }

        merkozesTabla   = (TableLayout) (findViewById(R.id.table_main));
        csapatok1       = (Spinner)     (findViewById(R.id.spinner1));
        csapatok2       = (Spinner)     (findViewById(R.id.spinner2));
        er1             = (TextView)    (findViewById(R.id.editText));
        er2             = (TextView)    (findViewById(R.id.editText2));

        initT();
    }

    //TODO (szgabbor): Mi ez a nagy T?
    //(szlev): Tabla
    private void refreshT()
    {
        merkozesTabla.removeAllViews();

        logika.tablaRajzol(merkozesTabla, this);
    }

    private void initT()
    {
        refreshT();

        er1.setText("0");
        er2.setText("0");

        List<String> list = new ArrayList<String>();
        list.add("");

        for(int i = 0; i< Utilities.csapatok.size(); i++)
        {
            list.add(Utilities.csapatok.get(i).getNev());
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

        refreshT();

        er1.setText("0");
        er2.setText("0");
    }

    //onClick action
    public void actionOnTovabbButton(View v)
    {
        Utilities.startNewActivity(KiertekelACA.class, this);
    }

}
