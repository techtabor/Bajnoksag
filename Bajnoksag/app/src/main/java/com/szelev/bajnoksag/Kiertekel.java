package com.szelev.bajnoksag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        //TODO (szgabbor): Jó, ha a konstruktorban nem történik semmi.
        kiirErtekeles();
    }

    private void kiirErtekeles()
    {
        //TODO (szgabbor): Ez itt kódismétlés
        TableRow tbr = new TableRow(mainAct);
        TextView t1v = new TextView(mainAct);
        t1v.setText("     Helyezés     ");
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        tbr.addView(t1v);
        TextView t2v = new TextView(mainAct);
        t2v.setText("     Csapatnév     ");
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        tbr.addView(t2v);
        ertekeloTabla.addView(tbr);

        for(int i=0; i<Kiertekel.sorrend.size(); i++)
        {
            tbr = new TableRow(mainAct);
            t1v = new TextView(mainAct);
            t1v.setText(Integer.toString(i+1));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbr.addView(t1v);
            t2v = new TextView(mainAct);
            t2v.setText(MainActivity.csapatok.get(Kiertekel.sorrend.get(i)).getNev());
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbr.addView(t2v);
            ertekeloTabla.addView(tbr);
        }
    }

    public static void sorrendKiszamol()
    {
        sorrend = new ArrayList<>();

        // kezdetben a csapatok index szerint vannak sorbarendezve

        for(int i=0; i<MainActivity.csapatok.size(); i++)
            sorrend.add(i);
        sorbarakPontszam(0, MainActivity.csapatok.size()-1);
    }

    private static void sorbarakPontszam(int kezdet, int veg)
    {
        // pontszamokat tarolo arrayList inicializalasa

        ArrayList<ArrayList<Integer>> pontszamok = new ArrayList<>();   // (veg-kezdet+1)x2-es arrayList, az elso elem az index, a masodik a pontszam
        for(int i=0; i<veg-kezdet+1; i++)
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(sorrend.get(kezdet+i));
            al.add(0);
            pontszamok.add(al);
        }

        // pontszamok kiszamitasa

        for(int i=0; i<pontszamok.size(); i++)
        {
            for(int j=0; j<pontszamok.size(); j++)
            {
                if(i!=j)
                {
                    //TODO (szgabbor): Ez itt kódismétlés
                    if(Kormerkozesek.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getElso()>Kormerkozesek.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getMasodik())
                    {
                        pontszamok.get(i).set(1,pontszamok.get(i).get(1)+KiertekelesBeallitasok.gyozelemPont);
                    } else if (Kormerkozesek.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getElso()<Kormerkozesek.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getMasodik())
                    {
                        pontszamok.get(i).set(1,pontszamok.get(i).get(1)+KiertekelesBeallitasok.veresegPont);
                    } else if (Kormerkozesek.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).voltMeccs())
                    {
                        pontszamok.get(i).set(1,pontszamok.get(i).get(1)+KiertekelesBeallitasok.dontetlenPont);
                    } else
                    {
                        pontszamok.get(i).set(1,pontszamok.get(i).get(1)+KiertekelesBeallitasok.nemVoltMegMeccsPont);
                    }
                }
            }
        }

        // pontszámok szerinti rendezés
        //TODO (szgabbor): Ez itt elég bonyolultnak tűnik nekem.
        Collections.sort(pontszamok, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return (o1.get(1).compareTo(o2.get(1)))*(-1);
            }
        });

        for(int i=0; i<pontszamok.size(); i++)
        {
            Kiertekel.sorrend.set(kezdet+i, pontszamok.get(i).get(0));
        }

        int azonoskezdet = 0;
        for(int i=1; i<pontszamok.size(); i++) {
            if (pontszamok.get(azonoskezdet).get(1) != pontszamok.get(i).get(1)) {
                if (azonoskezdet != i - 1) {
                    sorbarakPontszam(kezdet + azonoskezdet, kezdet + i - 1);
                }
                azonoskezdet = i;
            }
        }

        if(azonoskezdet != 0)
        {
            sorbarakPontszam(kezdet + azonoskezdet, kezdet + pontszamok.size()-1);
        }

    }
}