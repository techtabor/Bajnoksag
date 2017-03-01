package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableRow;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.Utilities;

/**
 * Created by Levente on 2017.01.11..
 */

public class CsapatHozzaadasL {

    //private Csapat lastCsapat;

    public CsapatHozzaadasL()
    {

    }

    public void addCsapat(int ID, String nev/*, int suly*/)
    {
        Csapat c = new Csapat(ID, nev/*, suly*/);
        Utilities.csapatok.add(c);

        //lastCsapat = c;
    }

    public void addCsapat(String nev/*, int suly*/)
    {
        addCsapat(Utilities.csapatok.size(), nev/*, suly*/);
    }

    /*/public TableRow getLastCsapatRow(AppCompatActivity act)
    {

        return Utilities.createRowWithOneCell(" " + lastCsapat.getNev() + " ", act);
        //return Utilities.createRowWithTwoCell(" " + lastCsapat.getNev() + " ", " " + lastCsapat.getSuly() + " ", act);
    }*/

    public TableRow getCsapatRowByIndex(int index, AppCompatActivity act)
    {
        String s1, s2, s3;
        s1 = Utilities.csapatok.get(index*3).getNev();
        if(Utilities.csapatok.size() > index*3+1)
        {
            s2 = Utilities.csapatok.get(index*3+1).getNev();
            if(Utilities.csapatok.size() > index*3+2)
            {
                s3 = Utilities.csapatok.get(index*3+2).getNev();
            }
            else
            {
                s3 = "";
            }
        }
        else
        {
            s2 = "";
            s3 = "";
        }

        return Utilities.createRowWithThreeCell(" " + s1 + " ", " " + s2 + " ", " " + s3 + " ", act);
        //return Utilities.createRowWithOneCell(" " + Utilities.csapatok.get(index).getNev() + " ", act);
        //return Utilities.createRowWithTwoCell(" " + Utilities.csapatok.get(index).getNev() + " ", " " + Utilities.csapatok.get(index).getSuly() + " ", act);
    }

}
