package com.szelev.bajnoksag.logic;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
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
    private AppCompatActivity appCompAct;

    private TableLayout merkTabl, tovabbTabl;
    private TableRow    szintValasztoTablR;

    public Egyeneskieses()
    {

    }

    public void init(TableLayout merkT, TableLayout tovabbT, TableRow szintValasztoTR, AppCompatActivity aca)
    {
        szintek = new ArrayList<>();
        aktualisSzint = -1;

        merkTabl = merkT;
        tovabbTabl = tovabbT;
        szintValasztoTablR = szintValasztoTR;

        appCompAct = aca;

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
        if(szintek.size()>0)
        {
            Teams.getTovabbjutok().clear();
            for(int i=0; i<szintek.get(szintek.size()-1).getTovabbjutok().size(); i++)
                Teams.getTovabbjutok().add(szintek.get(szintek.size()-1).getTovabbjutok().get(i));
        }
        esz.init();
        esz.general();
        szintek.add(esz);
        aktualisSzint++;
        refreshKiiras(getAktualisSzint(), appCompAct);
        szintValasztoTablR.addView(createSzintButton(aktualisSzint));
    }

    private Button createSzintButton(final int  szint)
    {
        Button b = new Button(appCompAct);
        b.setText((szint+1) + ". kör");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshKiiras(szint, appCompAct);
            }
        });

        return b;
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

            if(mindLejatszott(szint) && szint == szintek.size()-1)
            {
                Button tovabb = new Button(aca);
                tovabb.setText("Sorsol");
                tovabb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        general();
                    }
                });

                tovabbTabl.addView(tovabb);
            }
        }
    }

    private boolean mindLejatszott(int szint)
    {
        if(szintek.get(szint).getLeNemJatszott() > 0)
            return false;
        return true;
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
            Button b = createSetterButton(et1, et2, m.getIndex(), aca);
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

    private Button createSetterButton(final EditText et1, final EditText et2, final int index, final AppCompatActivity aca)
    {
        Button b = new Button(aca);
        b.setText("MENT");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meccsLejatsz(index, et1, et2, aca);
                refreshKiiras(aktualisSzint, aca);
            }
        });
        return b;
    }

    private void meccsLejatsz(int index, EditText et1, EditText et2, AppCompatActivity aca)
    {

        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();

        if(!s1.equals("") && !s2.equals(""))
        {
            szintek.get(aktualisSzint).getMerkozes(index).lejatszas(Integer.parseInt(s1), Integer.parseInt(s2));
            szintek.get(aktualisSzint).getTovabbjutok().add(szintek.get(aktualisSzint).getMerkozes(index).getGyoztes());
            szintek.get(aktualisSzint).decreaseLeNemJatszott();
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
