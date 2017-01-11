package com.szelev.bajnoksag.Logic;

import android.support.v7.app.AppCompatActivity;
import android.widget.TableRow;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.Utilities;

/**
 * Created by Levente on 2017.01.11..
 */

public class CsapatHozzaadasL {

    private static Csapat lastCsapat;

    public static void addCsapat(int ID, String nev, int suly)
    {
        Csapat c = new Csapat(ID, nev, suly);
        Utilities.csapatok.add(c);

        CsapatHozzaadasL.lastCsapat = c;
    }

    public static void addCsapat(String nev, int suly)
    {
        CsapatHozzaadasL.addCsapat(Utilities.csapatok.size(), nev, suly);
    }

    public static TableRow getLastCsapatRow(AppCompatActivity act)
    {
        return Utilities.createRowWithTwoCell(" " + CsapatHozzaadasL.lastCsapat.getNev() + " ", " " + CsapatHozzaadasL.lastCsapat.getSuly() + " ", act);
    }

    public static TableRow getCsapatRowByIndex(int index, AppCompatActivity act)
    {
        return Utilities.createRowWithTwoCell(" " + Utilities.csapatok.get(index).getNev() + " ", " " + Utilities.csapatok.get(index).getSuly() + " ", act);
    }

}
