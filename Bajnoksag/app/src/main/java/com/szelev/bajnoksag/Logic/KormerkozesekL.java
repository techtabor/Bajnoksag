package com.szelev.bajnoksag.Logic;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.MerkozesEredmeny;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;

/**
 * Created by Levente on 2017.01.24..
 */

public class KormerkozesekL {

    public ArrayList<ArrayList<MerkozesEredmeny>> eredmenyek;

    public KormerkozesekL()
    {
        initEredmenyek();
    }

    private void initEredmenyek()
    {
        eredmenyek = new ArrayList<>();
        int N = Utilities.csapatok.size();
        for(int i=0; i<N; i++)
        {
            ArrayList<MerkozesEredmeny> merkEr = new ArrayList<>();
            for(int j=0; j<N; j++)
            {
                MerkozesEredmeny merk = new MerkozesEredmeny();
                merkEr.add(merk);
            }
            eredmenyek.add(merkEr);
        }
    }

    public void setEredmeny(int i, int j, int egyik, int masik)
    {
        eredmenyek.get(i).get(j).setEredmeny(egyik, masik);
    }

    public void tablaRajzol(TableLayout tabl, final AppCompatActivity aca)
    {
        TableRow tr = new TableRow(aca);
        TextView tv;

        tv = Utilities.createTextView("", aca);
        tr.addView(tv);

        for(int i = 0; i< Utilities.csapatok.size(); i++)
        {
            tv = Utilities.createTextView(" " + Utilities.csapatok.get(i).getNev() + " ", aca);
            tr.addView(tv);
        }
        tabl.addView(tr);

        for(int i = 0; i< Utilities.csapatok.size(); i++)
        {
            tr = new TableRow(aca);
            tv = Utilities.createTextView(" " + Utilities.csapatok.get(i).getNev() + " ", aca);
            tr.addView(tv);

            for(int j = 0; j< Utilities.csapatok.size(); j++)
            {
                if(eredmenyek.get(i).get(j).voltMeccs())
                {
                    tv = Utilities.createTextView(" "+eredmenyek.get(i).get(j).getElso()+":"+eredmenyek.get(i).get(j).getMasodik()+" ", aca);
                }
                else
                {
                    tv = Utilities.createTextView(" - ", aca);
                }
                final int finalI = i;
                final int finalJ = j;
                tv.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Spinner sp1 = (Spinner)     (aca.findViewById(R.id.spinner1));
                                Spinner sp2 = (Spinner)     (aca.findViewById(R.id.spinner2));

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
