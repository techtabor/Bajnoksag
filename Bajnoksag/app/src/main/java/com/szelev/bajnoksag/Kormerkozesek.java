package com.szelev.bajnoksag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Levente on 2016.12.06..
 */

public class Kormerkozesek extends AppCompatActivity {

    private TableLayout merkozesTabla;
    private MainActivity mainAct;

    public static ArrayList<ArrayList<Eredmeny>> eredmenyek;
    public Kormerkozesek(MainActivity mainAct)
    {
        this.mainAct = mainAct;

        merkozesTabla = (TableLayout)   (mainAct.findViewById(R.id.table_main));

        initTable();
    }

    private void initTable()
    {
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



}
