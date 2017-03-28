package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableRow;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.util.DrawTable;

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
        DrawTable.csapatok.add(c);

        //lastCsapat = c;
    }

    public void addCsapat(String nev/*, int suly*/)
    {
        addCsapat(DrawTable.csapatok.size(), nev/*, suly*/);
    }

    /*/public TableRow getLastCsapatRow(AppCompatActivity act)
    {

        return DrawTable.createRowWithOneCell(" " + lastCsapat.getNev() + " ", act);
        //return DrawTable.createRowWithTwoCell(" " + lastCsapat.getNev() + " ", " " + lastCsapat.getSuly() + " ", act);
    }*/

    public TableRow getCsapatRowByIndex(int index, AppCompatActivity act)
    {
        String s1, s2, s3;
        s1 = DrawTable.csapatok.get(index*3).getNev();
        if(DrawTable.csapatok.size() > index*3+1)
        {
            s2 = DrawTable.csapatok.get(index*3+1).getNev();
            if(DrawTable.csapatok.size() > index*3+2)
            {
                s3 = DrawTable.csapatok.get(index*3+2).getNev();
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

        return DrawTable.createRowWithThreeCell(" " + s1 + " ", " " + s2 + " ", " " + s3 + " ", act);
        //return DrawTable.createRowWithOneCell(" " + DrawTable.csapatok.get(index).getNev() + " ", act);
        //return DrawTable.createRowWithTwoCell(" " + DrawTable.csapatok.get(index).getNev() + " ", " " + DrawTable.csapatok.get(index).getSuly() + " ", act);
    }

}
