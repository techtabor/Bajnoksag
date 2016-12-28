package com.szelev.bajnoksag.AppCompatActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.Utilities;

/**
 * Created by Levente on 2016.12.21..
 */

public class ModvalasztoACA extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modvalaszto);
    }

    //onClick event
    public void actionOnKorButton(View v)
    {
        Utilities.startNewActivity(KormerkozesekACA.class, this);
    }

    //onClick event
    public void actionOnEgyenesButton(View v)
    {
        Utilities.startNewActivity(EgyeneskiesesACA.class, this);
    }

}
