package com.szelev.bajnoksag;

import android.graphics.Color;
import android.opengl.EGLExt;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Levente on 2016.12.06..
 */

public class Kormerkozesek extends AppCompatActivity implements View.OnClickListener{

    private TableLayout     merkozesTabla;
    private MainActivity    mainAct;
    private Spinner         csapatok1, csapatok2;
    private Button          mentes;
    private Button          tovabb;
    private TextView        er1, er2;

    public static ArrayList<ArrayList<Eredmeny>> eredmenyek;
    public Kormerkozesek(MainActivity mainAct)
    {
        this.mainAct = mainAct;

        merkozesTabla   = (TableLayout) (mainAct.findViewById(R.id.table_main));
        csapatok1       = (Spinner)     (mainAct.findViewById(R.id.spinner1));
        csapatok2       = (Spinner)     (mainAct.findViewById(R.id.spinner2));
        mentes          = (Button)      (mainAct.findViewById(R.id.button));
        er1             = (TextView)    (mainAct.findViewById(R.id.editText));
        er2             = (TextView)    (mainAct.findViewById(R.id.editText2));
        tovabb          = (Button)      (mainAct.findViewById(R.id.button3));

        mentes.setOnClickListener(this);
        tovabb.setOnClickListener(this);

        initT();
    }

    private void refreshT()
    {
        merkozesTabla.removeAllViews();

        TableRow tr = new TableRow(mainAct);
        TextView tv = new TextView(mainAct);
        tv.setText("");
        tr.addView(tv);
        for(int i=0; i<MainActivity.csapatok.size(); i++)
        {
            tv = new TextView(mainAct);
            tv.setText(" " + MainActivity.csapatok.get(i).getNev() + " ");
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);
        }
        merkozesTabla.addView(tr);

        for(int i=0; i<MainActivity.csapatok.size(); i++)
        {
            tr = new TableRow(mainAct);
            tv = new TextView(mainAct);
            tv.setText(" " + MainActivity.csapatok.get(i).getNev() + " ");
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tr.addView(tv);

            for(int j=0; j<MainActivity.csapatok.size(); j++)
            {
                tv = new TextView(mainAct);
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

        for(int i=0; i<MainActivity.csapatok.size(); i++)
        {
            list.add(MainActivity.csapatok.get(i).getNev());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mainAct, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        csapatok1.setAdapter(dataAdapter);
        csapatok2.setAdapter(dataAdapter);
    }

    public static void initEredmenyek()
    {
        eredmenyek = new ArrayList<>();
        for(int i=0; i<MainActivity.csapatok.size(); i++)
        {
            ArrayList<Eredmeny> al = new ArrayList<>();
            for(int j=0; j<MainActivity.csapatok.size(); j++)
            {
                Eredmeny e = new Eredmeny();
                al.add(e);
            }
            eredmenyek.add(al);
        }
    }


    @Override
    public void onClick(View v) {
        if(mainAct.findViewById(R.id.button).equals(v))
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
        } else {
            MainActivity.activity_number = 2;
            Kiertekel.sorrendKiszamol();
            mainAct.create();
        }
    }
}
