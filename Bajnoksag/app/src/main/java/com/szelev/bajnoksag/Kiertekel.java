package com.szelev.bajnoksag;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Levente on 2016.12.18..
 */

public class Kiertekel extends AppCompatActivity {

    private MainActivity                mainAct;
    private TableLayout                 ertekeloTabla;
    public  static ArrayList<Integer>   sorrend;


    public Kiertekel(MainActivity mainAct)
    {
        this.mainAct = mainAct;

        ertekeloTabla   = (TableLayout) (mainAct.findViewById(R.id.table_main));


    }

    public static void sorrendKiszamol()
    {
        sorrend = new ArrayList<>();
        for(int i=0; i<MainActivity.csapatok.size(); i++)
            sorrend.add(i);
        sorbarak(0, MainActivity.csapatok.size()-1);
    }

    private static void sorbarak(int kezdet, int veg)
    {
        ArrayList<ArrayList<Integer>> pontszamok = new ArrayList<>();
        for(int i=0; i<veg-kezdet+1; i++)
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(sorrend.get(kezdet+i));
            al.add(0);
            pontszamok.add(al);
        }


    }
}