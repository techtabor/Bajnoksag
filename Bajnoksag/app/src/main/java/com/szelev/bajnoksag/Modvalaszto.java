package com.szelev.bajnoksag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Levente on 2016.12.21..
 */

public class Modvalaszto extends AppCompatActivity implements View.OnClickListener {

    //private MainActivity    mainAct;
    private Button          mod1, mod2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modvalaszto);

        init();
    }


    public Modvalaszto()
    {

    }

    private void init()
    {
        mod1 = (Button) (findViewById(R.id.button8));
        mod2 = (Button) (findViewById(R.id.button9));

        mod1.setOnClickListener(this);
        mod2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(findViewById(R.id.button8).equals(v))
        {
            MainActivity.activity_number = 1;
            Kormerkozesek.initEredmenyek();             // ha majd kesobb hozza lehet utolag adni csapatot, akkor ezt mashogy kell megvalositani.
        }
        else if(findViewById(R.id.button9).equals(v))
        {
            MainActivity.activity_number = 5;
        }
    }

}
