package com.szelev.bajnoksag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Levente on 2016.12.06..
 */

public class CsapatHozzaAdas extends AppCompatActivity implements View.OnClickListener{

    private Button              ujCsapatBut;
    private Button              tovabbBut;
    private EditText            csapatNevText;
    private EditText            csapatSulyText;
    private TableLayout         csapatTabla;

    private MainActivity        mainAct;

    public CsapatHozzaAdas(MainActivity mainAct)
    {
        this.mainAct = mainAct;

        ujCsapatBut     = (Button)      (mainAct.findViewById(R.id.buttonUjCsapat));
        tovabbBut       = (Button)      (mainAct.findViewById(R.id.button2));
        csapatNevText   = (EditText)    (mainAct.findViewById(R.id.editTextCsapatNev));
        csapatSulyText  = (EditText)    (mainAct.findViewById(R.id.editTextCsapatSuly));
        csapatTabla     = (TableLayout) (mainAct.findViewById(R.id.table_main));

        initTable();

        ujCsapatBut.setOnClickListener(this);
        tovabbBut.setOnClickListener(this);
    }

    private void initTable() {
        TableRow tbr = new TableRow(mainAct);
        /*TextView t1v = new TextView(mainAct);
        t1v.setText("  Csapat ID  ");
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        tbr.addView(t1v);*/
        TextView t2v = new TextView(mainAct);
        t2v.setText("     Csapatnév     ");
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        tbr.addView(t2v);
        TextView t3v = new TextView(mainAct);
        t3v.setText("     Csapatsúly     ");
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        tbr.addView(t3v);
        csapatTabla.addView(tbr);

        for(int i=0; i<MainActivity.csapatok.size(); i++) {
            Csapat c = MainActivity.csapatok.get(i);

            tbr = new TableRow(mainAct);
            /*t1v = new TextView(mainAct);
            t1v.setText(Integer.toString(c.getID()));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbr.addView(t1v);*/
            t2v = new TextView(mainAct);
            t2v.setText(" " + c.getNev() + " ");
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbr.addView(t2v);
            t3v = new TextView(mainAct);
            t3v.setText(" " + Integer.toString(c.getSuly()) + " ");
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbr.addView(t3v);
            csapatTabla.addView(tbr);
        }
    }

    @Override
    public void onClick(View v) {
        if(mainAct.findViewById(R.id.buttonUjCsapat).equals(v)) {
            Csapat c = new Csapat();

            c.setID(MainActivity.csapatok.size());
            c.setNev(csapatNevText.getText().toString());
            c.setSuly(Integer.parseInt(csapatSulyText.getText().toString()));

            csapatNevText.setText("");
            MainActivity.csapatok.add(c);

            TableRow tbr = new TableRow(mainAct);
            /*TextView t1v = new TextView(mainAct);
            t1v.setText(Integer.toString(c.getID()));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbr.addView(t1v);*/
            TextView t2v = new TextView(mainAct);
            t2v.setText(" " + c.getNev() + " ");
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbr.addView(t2v);
            TextView t3v = new TextView(mainAct);
            t3v.setText(" " + Integer.toString(c.getSuly()) + " ");
            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbr.addView(t3v);
            csapatTabla.addView(tbr);
        } else {
            MainActivity.activity_number = 1;
            mainAct.create();

        }

    }

}