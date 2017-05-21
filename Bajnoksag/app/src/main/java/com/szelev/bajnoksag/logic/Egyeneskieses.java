package com.szelev.bajnoksag.logic;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.data.Merkozes;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Levente on 2017.01.30..
 */

public class Egyeneskieses {

    private ArrayList<EgyeneskiesesSzint> szintek;
    private int aktualisSzint;

    private TableLayout merkTabl, tovabbTabl;

    public Egyeneskieses()
    {

    }

    public void init(TableLayout merkT, TableLayout tovabbT)
    {
        szintek = new ArrayList<>();
        aktualisSzint = 0;

        merkTabl = merkT;
        tovabbT = tovabbT;

        initTovabbjutok();
    }

    private void initTovabbjutok()
    {
        Teams.getTovabbjutok().clear();
        for(int i=0; i<Teams.numOfTeams(); i++)
        {
            Teams.getTovabbjutok().add(Teams.getTeam(i));
        }
    }

    public void general()
    {
        EgyeneskiesesSzint esz = new EgyeneskiesesSzint();
        esz.init();
        esz.general();
        szintek.add(esz);
        aktualisSzint++;
    }

    public int getAktualisSzint() {
        return aktualisSzint;
    }

    public void refreshKiiras(int szint, AppCompatActivity aca)
    {
        if(szint < szintek.size())
        {
            merkTabl.removeAllViews();
            tovabbTabl.removeAllViews();

            tovabbjutokKirajzol(merkTabl, aca);
            merkozesekKirajzol(tovabbTabl, aca);

            aktualisSzint = szint;
        }
    }

    private void merkozesekKirajzol(TableLayout tabl, final AppCompatActivity aca)
    {
        for(int i=0; i<szintek.get(aktualisSzint).getNumOfMerkozes(); i++)
        {
            merkozesKirajzol(szintek.get(aktualisSzint).getMerkozes(i), tabl, aca);
        }
    }

    // TODO (szgabbor): ez itt nem logika.
    private void merkozesKirajzol(Merkozes m, TableLayout tabl, AppCompatActivity aca)
    {
        TableRow tr1 = DrawTable.createRowWithThreeCell(" " + m.getCs1().getNev() + " ", "  -  ", " " + m.getCs2().getNev() + " ", aca);
        TableRow tr2;

        if(m.isLejatszott())
        {
            tr2 = DrawTable.createRowWithThreeCell(" " + m.getEredmeny1() + " ", "  :  ", " " + m.getEredmeny2() + " ", aca);
        } else {
            EditText et1 = createNumEditText(10, aca);
            EditText et2 = createNumEditText(10, aca);
            m.setInputId1(et1.getId());
            m.setInputId2(et2.getId());
            TextView tv = DrawTable.createTextView("  :  ", aca);
            Button b = createSetterButton(m.getIndex(), aca);
            tr2 = new TableRow(aca);
            tr2.addView(et1);
            tr2.addView(tv);
            tr2.addView(et2);
            tr2.addView(b);
        }

        tabl.addView(tr1);
        tabl.addView(tr2);
    }

    private EditText createNumEditText(final int minWidth, final AppCompatActivity aca)
    {
        EditText et = new EditText(aca);
        et.setMinWidth(minWidth);
        et.setInputType(InputType.TYPE_CLASS_NUMBER);
        return et;
    }

    private Button createSetterButton(final int index, final AppCompatActivity aca)
    {
        Button b = new Button(aca);
        b.setText("MENT");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meccsLejatsz(index, aca);
                refreshKiiras(aktualisSzint, aca);
            }
        });
        return b;
    }

    private void meccsLejatsz(int index, AppCompatActivity aca)
    {
        String s1 = String.valueOf(((EditText)(aca.findViewById(szintek.get(aktualisSzint).getMerkozes(index).getInputId1()))).getText());
        String s2 = String.valueOf(((EditText)(aca.findViewById(szintek.get(aktualisSzint).getMerkozes(index).getInputId2()))).getText());
        if(!s1.equals("") && !s2.equals(""))
        {
            szintek.get(aktualisSzint).getMerkozes(index).lejatszas(Integer.parseInt(s1), Integer.parseInt(s1));
        }
    }

    private void tovabbjutokKirajzol(TableLayout tabl, AppCompatActivity aca)
    {
        tabl.addView(DrawTable.createRowWithOneCell("Továbbjutók:", aca));
        for(int i=0; i<szintek.get(aktualisSzint).getTovabbjutok().size(); i++)
        {
            tabl.addView(DrawTable.createRowWithOneCell(" "+szintek.get(aktualisSzint).getTovabbjutok().get(i).getNev()+" ", aca));
        }
    }

}
