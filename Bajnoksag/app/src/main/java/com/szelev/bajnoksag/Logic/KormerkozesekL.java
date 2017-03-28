package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.data.MerkozesEredmeny;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.util.DrawTable;

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
        int N = DrawTable.csapatok.size();
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

        tv = DrawTable.createTextView("", aca);
        tr.addView(tv);
        tv = DrawTable.createTextView("", aca);
        tr.addView(tv);

        for(int i = 0; i< DrawTable.csapatok.size(); i++)
        {
            tv = DrawTable.createTextView(" " + (i+1) + " ", aca, 120);
            tr.addView(tv);
        }
        tabl.addView(tr);

        for(int i = 0; i< DrawTable.csapatok.size(); i++)
        {
            tr = new TableRow(aca);
            tv = DrawTable.createTextView(" " + (i+1) + " ", aca, 30);
            tr.addView(tv);
            tv = DrawTable.createTextView(" " + DrawTable.csapatok.get(i).getNev() + " ", aca);
            tr.addView(tv);

            for(int j = 0; j< DrawTable.csapatok.size(); j++)
            {
                if(eredmenyek.get(i).get(j).voltMeccs())
                {
                    tv = DrawTable.createTextView(" "+eredmenyek.get(i).get(j).getElso()+":"+eredmenyek.get(i).get(j).getMasodik()+" ", aca);
                }
                else
                {
                    tv = DrawTable.createTextView(" - ", aca);
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
