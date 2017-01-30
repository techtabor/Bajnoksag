package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.szelev.bajnoksag.Logic.EgyeneskiesesL;
import com.szelev.bajnoksag.R;

/**
 * Created by Levente on 2016.12.21..
 */

public class EgyeneskiesesACA extends AppCompatActivity{

    private static EgyeneskiesesL   logika;
    private static boolean          vanLogika = false;

    private TableLayout merkozesTabla;
    private TableLayout tovabbjutokTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyeneskieses);

        init();
    }

    public void init()
    {
        if(!vanLogika)
        {
            logika = new EgyeneskiesesL();
            logika.init();
            logika.general();
            vanLogika = true;
        }

        merkozesTabla       = (TableLayout) (findViewById(R.id.table_main));
        tovabbjutokTabla    = (TableLayout) (findViewById(R.id.table_main2));

        bajnoksagAllapotKiir();
    }

    private void bajnoksagAllapotKiir()
    {
        logika.refreshKiiras(merkozesTabla, tovabbjutokTabla, this);
    }

}
