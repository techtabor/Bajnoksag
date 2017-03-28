package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.R;

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
        CreateActivity.start(KormerkozesekACA.class, this);
    }

    //onClick event
    public void actionOnEgyenesButton(View v)
    {
        CreateActivity.start(EgyeneskiesesACA.class, this);
    }

}
