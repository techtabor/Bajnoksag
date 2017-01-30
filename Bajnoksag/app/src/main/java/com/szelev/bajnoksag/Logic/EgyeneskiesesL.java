package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Levente on 2017.01.30..
 */

public class EgyeneskiesesL {

    private ArrayList<Csapat> csapatok;
    private ArrayList<ArrayList<Csapat>> merkozesek;

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
            ArrayList<Csapat> alc = new ArrayList<>();
            alc.add(getRandomCsapatAndRemove());
            alc.add(getRandomCsapatAndRemove());
            merkozesek.add(alc);
        }
    }

    public void merkozesKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(Utilities.createRowWithOneCell("Még le nem játszott mérkőzések:", aca));
        for(int i=0; i<merkozesek.size(); i++)
        {
            View v = Utilities.createRowWithTwoCell(" "+merkozesek.get(i).get(0).getNev()+" ", " "+merkozesek.get(i).get(1).getNev()+" ", aca);
            tabl.addView(v);
        }
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
