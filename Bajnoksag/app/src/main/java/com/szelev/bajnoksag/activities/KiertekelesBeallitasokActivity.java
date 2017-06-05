package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.saveable;
import com.szelev.bajnoksag.util.CreateActivity;

/**
 * Created by Levente on 2016.12.19..
 */

public class KiertekelesBeallitasokActivity extends AppCompatActivity implements saveable {

    private EditText    tv1, tv2, tv3, tv4;

    public static int gyozelemPont          = 3;
    public static int dontetlenPont         = 1;
    public static int veresegPont           = 0;
    public static int nemVoltMegMeccsPont   = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiertekelesbeallitasok);

        init();
    }

    public void loadFromString(String s)
    {
        String[] split = s.trim().split("\\s+");
        gyozelemPont = Integer.parseInt(split[0]);
        dontetlenPont = Integer.parseInt(split[1]);
        veresegPont = Integer.parseInt(split[2]);
        nemVoltMegMeccsPont = Integer.parseInt(split[3]);
    }

    public String toString()
    {
        String s;
        s = "";
        s += Integer.toString(gyozelemPont) + " " + Integer.toString(dontetlenPont) + " " + Integer.toString(veresegPont) + " " + Integer.toString(nemVoltMegMeccsPont) + "\n";
        return s;
    }

    private void init()
    {
        tv1 = (EditText) (findViewById(R.id.editText3));
        tv2 = (EditText) (findViewById(R.id.editText4));
        tv3 = (EditText) (findViewById(R.id.editText5));
        tv4 = (EditText) (findViewById(R.id.editText6));

        tv1.setText(Integer.toString(KiertekelesBeallitasokActivity.gyozelemPont));
        tv2.setText(Integer.toString(KiertekelesBeallitasokActivity.dontetlenPont));
        tv3.setText(Integer.toString(KiertekelesBeallitasokActivity.veresegPont));
        tv4.setText(Integer.toString(KiertekelesBeallitasokActivity.nemVoltMegMeccsPont));
    }

    //onClick event
    public void actionOnBeallitButton(View v)
    {
        KiertekelesBeallitasokActivity.gyozelemPont         = Integer.parseInt(tv1.getText().toString());
        KiertekelesBeallitasokActivity.dontetlenPont        = Integer.parseInt(tv2.getText().toString());
        KiertekelesBeallitasokActivity.veresegPont          = Integer.parseInt(tv3.getText().toString());
        KiertekelesBeallitasokActivity.nemVoltMegMeccsPont  = Integer.parseInt(tv4.getText().toString());

        ((TextView) (findViewById(R.id.textView7))).setText("Módosítások elmentve!");
    }

    //onClick event
    public void actionOnVisszaButton(View v)
    {
        CreateActivity.start(KiertekelActivity.class, this);
        ((TextView) (findViewById(R.id.textView7))).setText("");
    }

}
