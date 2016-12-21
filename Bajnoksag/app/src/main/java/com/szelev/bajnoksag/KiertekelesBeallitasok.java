package com.szelev.bajnoksag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Levente on 2016.12.19..
 */

public class KiertekelesBeallitasok extends AppCompatActivity{

    private TextView        tv1, tv2, tv3, tv4;

    //TODO (szgabbor): Később ez konfigurálható lesz, azért szervezted ki?
    // (Igazából ráér akkor is ez a kiszervezés)
    public static int gyozelemPont          = 3;
    public static int dontetlenPont         = 1;
    public static int veresegPont           = 0;
    public static int nemVoltMegMeccsPont   = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiertekelesbeallitasok);
    }

    public KiertekelesBeallitasok()
    {
        tv1 = (TextView) (findViewById(R.id.editText3));
        tv2 = (TextView) (findViewById(R.id.editText4));
        tv3 = (TextView) (findViewById(R.id.editText5));
        tv4 = (TextView) (findViewById(R.id.editText6));

        tv1.setText(Integer.toString(KiertekelesBeallitasok.gyozelemPont));
        tv2.setText(Integer.toString(KiertekelesBeallitasok.dontetlenPont));
        tv3.setText(Integer.toString(KiertekelesBeallitasok.veresegPont));
        tv4.setText(Integer.toString(KiertekelesBeallitasok.nemVoltMegMeccsPont));
    }

    //onClick event
    public void actionOnBeallitButton(View v)
    {
        KiertekelesBeallitasok.gyozelemPont         = Integer.parseInt(tv1.getText().toString());
        KiertekelesBeallitasok.dontetlenPont        = Integer.parseInt(tv2.getText().toString());
        KiertekelesBeallitasok.veresegPont          = Integer.parseInt(tv3.getText().toString());
        KiertekelesBeallitasok.nemVoltMegMeccsPont  = Integer.parseInt(tv4.getText().toString());
    }

    //onClick event
    public void actionOnVisszaButton(View v)
    {
        Intent intent = new Intent(this, Kiertekel.class);

        startActivity(intent);
    }

}
