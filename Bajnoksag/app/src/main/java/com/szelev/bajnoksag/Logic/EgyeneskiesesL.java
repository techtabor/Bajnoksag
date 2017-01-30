package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
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

    public void merkozesKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(Utilities.createRowWithOneCell("Még le nem játszott mérkőzések:", aca));
        for(int i=0; i<merkozesek.size(); i++)
        {
            View v = Utilities.createRowWithTwoCell(" "+merkozesek.get(i).cs1.getNev()+" ", " "+merkozesek.get(i).cs2.getNev()+" ", aca);
            tabl.addView(v);
        }
    }

    private Merkozes getAndRemoveMerkozesByIndex(int merkozesIndex)
    {


        return null;
    }

    private TextView createTextViewWithSpecificOnClickListener(String text, AppCompatActivity aca, int merkozesIndex)
    {
        TextView tv = Utilities.createTextView(text, aca);
        tv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
        return tv;
    }

    public void tovabbjutokKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(Utilities.createRowWithOneCell("Továbbjutók:", aca));
        for(int i=0; i<csapatok.size(); i++)
        {
            tabl.addView(Utilities.createRowWithOneCell(" "+csapatok.get(i).getNev()+" ", aca));
        }
    }

}
