package com.szelev.bajnoksag;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class Kormerkozesek extends AppCompatActivity{

    private static boolean  voltEEredmenyInit = false;

    private TableLayout     merkozesTabla;
    private Spinner         csapatok1, csapatok2;
    private TextView        er1, er2;

    //TODO (szgabbor) Ez miért statikus?
    public static ArrayList<ArrayList<Eredmeny>> eredmenyek;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        if(!voltEEredmenyInit)
        {
            voltEEredmenyInit = true;
            initEredmenyek();
        }

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

        initT();
    }

    //TODO (szgabbor): Mi ez a nagy T?
    //(szlev): Tabla
    private void refreshT()
    {
        merkozesTabla.removeAllViews();

        TableRow tr = new TableRow(this);
        TextView tv = new TextView(this);
        tv.setText("");
        tr.addView(tv);
        for(int i = 0; i< CsapatHozzaAdas.csapatok.size(); i++)
        {
            //TODO (szgabbor): Ez itt kódismétlés.
            tv = new TextView(this);
            tv.setText(" " + CsapatHozzaAdas.csapatok.get(i).getNev() + " ");
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);
        }
        merkozesTabla.addView(tr);

        for(int i = 0; i<CsapatHozzaAdas.csapatok.size(); i++)
        {
            tr = new TableRow(this);
            tv = new TextView(this);
            tv.setText(" " + CsapatHozzaAdas.csapatok.get(i).getNev() + " ");
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);

            for(int j = 0; j<CsapatHozzaAdas.csapatok.size(); j++)
            {
                tv = new TextView(this);
                if(eredmenyek.get(i).get(j).voltMeccs)
                {
                    tv.setText(" "+eredmenyek.get(i).get(j).getElso()+":"+eredmenyek.get(i).get(j).getMasodik()+" ");
                }
                else
                {
                    tv.setText(" - ");
                }
                tv.setTextColor(Color.BLACK);
                tv.setGravity(Gravity.CENTER);
                tr.addView(tv);
            }

            merkozesTabla.addView(tr);
        }


    }

    private void initT()
    {
        refreshT();

        er1.setText("0");
        er2.setText("0");

        List<String> list = new ArrayList<String>();
        list.add("");

        for(int i = 0; i< CsapatHozzaAdas.csapatok.size(); i++)
        {
            list.add(CsapatHozzaAdas.csapatok.get(i).getNev());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        csapatok1.setAdapter(dataAdapter);
        csapatok2.setAdapter(dataAdapter);
    }

    private static void initEredmenyek()
    {
        eredmenyek = new ArrayList<>();
        for(int i = 0; i< CsapatHozzaAdas.csapatok.size(); i++)
        {
            ArrayList<Eredmeny> al = new ArrayList<>();
            for(int j = 0; j< CsapatHozzaAdas.csapatok.size(); j++)
            {
                Eredmeny e = new Eredmeny();
                al.add(e);
            }
            eredmenyek.add(al);
        }
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
            eredmenyek.get(i).get(j).setEredmeny(Integer.parseInt(er1.getText().toString()), Integer.parseInt(er2.getText().toString()));
            eredmenyek.get(j).get(i).setEredmeny(Integer.parseInt(er2.getText().toString()), Integer.parseInt(er1.getText().toString()));
        }

        refreshT();

        er1.setText("0");
        er2.setText("0");
    }

    //onClick action
    public void actionOnTovabbButton(View v)
    {
        Intent intent = new Intent(this, Kiertekel.class);

        startActivity(intent);
    }

}
