package com.szelev.bajnoksag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Levente on 2016.12.21..
 */

public class Modvalaszto extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modvalaszto);
    }

    //onClick event
    public void actionOnKorButton(View v)
    {
        Intent intent = new Intent(this, Kormerkozesek.class);

        startActivity(intent);
    }

    //onClick event
    public void actionOnEgyenesButton(View v)
    {
        Intent intent = new Intent(this, Egyeneskieses.class);

        startActivity(intent);
    }

}
