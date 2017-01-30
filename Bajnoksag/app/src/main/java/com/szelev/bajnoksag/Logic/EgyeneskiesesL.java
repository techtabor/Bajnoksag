package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.Merkozes;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Levente on 2017.01.30..
 */

public class EgyeneskiesesL {

    private ArrayList<Csapat> csapatok;
    private ArrayList<Merkozes> merkozesek;

    public EgyeneskiesesL()
    {

    }

    public void init()
    {
        csapatok    = new ArrayList<>();
        merkozesek  = new ArrayList<>();
        for(int i=0; i<Utilities.csapatok.size(); i++)
        {
            csapatok.add(Utilities.csapatok.get(i));
        }
    }

    private Csapat getRandomCsapatAndRemove()
    {
        Random ran = new Random();
        int index = ran.nextInt(csapatok.size());
        Csapat c = csapatok.get(index);
        csapatok.remove(index);
        return c;
    }

    public void general()
    {
        int meret = csapatok.size();
        for(int i=0; i<meret/2; i++)
        {
            Csapat cs1 = getRandomCsapatAndRemove();
            Csapat cs2 = getRandomCsapatAndRemove();
            merkozesek.add(new Merkozes(cs1, cs2, i));
        }
    }

    public void refreshKiiras(TableLayout tl1, TableLayout tl2, AppCompatActivity aca)
    {
        tl1.removeAllViews();
        tl2.removeAllViews();

        merkozesKirajzol(tl1, aca);
        tovabbjutokKirajzol(tl2, aca);
    }

    private void merkozesKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(Utilities.createRowWithOneCell("Még le nem játszott mérkőzések:", aca));
        for(int i=0; i<merkozesek.size(); i++)
        {
            TextView tv1 = createTextViewWithSpecificOnClickListener(merkozesek.get(i).cs1, aca, merkozesek.get(i).index);
            TextView tv2 = createTextViewWithSpecificOnClickListener(merkozesek.get(i).cs2, aca, merkozesek.get(i).index);

            TableRow tr = new TableRow(aca);
            tr.addView(tv1);
            tr.addView(tv2);

            tabl.addView(tr);
        }
    }

    private void removeMerkozesByIndex(int merkozesIndex)
    {
        int index = -1;
        for(int i=0; i<merkozesek.size() && index == -1; i++)
        {
            if(merkozesek.get(i).index == merkozesIndex)
                index = i;
        }
        merkozesek.remove(index);
    }

    private TextView createTextViewWithSpecificOnClickListener(final Csapat cs, AppCompatActivity aca, final int merkozesIndex)
    {
        TextView tv = Utilities.createTextView(cs.getNev(), aca);
        tv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeMerkozesByIndex(merkozesIndex);
                        csapatok.add(cs);
                    }
                }
        );
        return tv;
    }

    private void tovabbjutokKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(Utilities.createRowWithOneCell("Továbbjutók:", aca));
        for(int i=0; i<csapatok.size(); i++)
        {
            tabl.addView(Utilities.createRowWithOneCell(" "+csapatok.get(i).getNev()+" ", aca));
        }
    }

}
