package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

import com.szelev.bajnoksag.Logic.EgyeneskiesesL;
import com.szelev.bajnoksag.R;

/**
 * Created by Levente on 2016.12.21..
 */

public class EgyeneskiesesACA extends AppCompatActivity{

    private EgyeneskiesesL   logika;
    //private static boolean          vanLogika = false;

    private TableLayout merkozesTabla;
    private TableLayout tovabbjutokTabla;

    public EgyeneskiesesACA()
    {
        logika = new EgyeneskiesesL();
        initLogika();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyeneskieses);

        init();
    }

    private void initLogika()
    {
        logika.init();
        logika.general();
    }

    public void init()
    {
        /*if(!vanLogika)
        {
            logika = new EgyeneskiesesL();
            logika.init();
            logika.general();
            vanLogika = true;
        }*/

        merkozesTabla       = (TableLayout) (findViewById(R.id.table_main));
        tovabbjutokTabla    = (TableLayout) (findViewById(R.id.table_main2));

        bajnoksagAllapotKiir();
    }

    private void bajnoksagAllapotKiir()
    {
        logika.refreshKiiras(merkozesTabla, tovabbjutokTabla, this);
    }

}
