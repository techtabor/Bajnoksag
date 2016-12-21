package com.szelev.bajnoksag;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Levente on 2016.12.19..
 */

public class KiertekelesBeallitasok extends AppCompatActivity implements View.OnClickListener{

    private MainActivity    mainAct;
    private TextView        tv1, tv2, tv3, tv4;
    private Button          beallit;
    private Button          vissza;

    public static int gyozelemPont          = 3;
    public static int dontetlenPont         = 1;
    public static int veresegPont           = 0;
    public static int nemVoltMegMeccsPont   = 0;

    public KiertekelesBeallitasok(MainActivity mainAct)
    {
        this.mainAct = mainAct;

        tv1 = (TextView) (mainAct.findViewById(R.id.editText3));
        tv2 = (TextView) (mainAct.findViewById(R.id.editText4));
        tv3 = (TextView) (mainAct.findViewById(R.id.editText5));
        tv4 = (TextView) (mainAct.findViewById(R.id.editText6));

        tv1.setText(Integer.toString(KiertekelesBeallitasok.gyozelemPont));
        tv2.setText(Integer.toString(KiertekelesBeallitasok.dontetlenPont));
        tv3.setText(Integer.toString(KiertekelesBeallitasok.veresegPont));
        tv4.setText(Integer.toString(KiertekelesBeallitasok.nemVoltMegMeccsPont));

        beallit = (Button) (mainAct.findViewById(R.id.button6));
        vissza  = (Button) (mainAct.findViewById(R.id.button7));

        beallit.setOnClickListener(this);
        vissza.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(mainAct.findViewById(R.id.button6).equals(v))
        {
            KiertekelesBeallitasok.gyozelemPont         = Integer.parseInt(tv1.getText().toString());
            KiertekelesBeallitasok.dontetlenPont        = Integer.parseInt(tv2.getText().toString());
            KiertekelesBeallitasok.veresegPont          = Integer.parseInt(tv3.getText().toString());
            KiertekelesBeallitasok.nemVoltMegMeccsPont  = Integer.parseInt(tv4.getText().toString());

        }
        else
        {
            MainActivity.activity_number = 2;
            Kiertekel.sorrendKiszamol();
            mainAct.create();
        }
    }

}
