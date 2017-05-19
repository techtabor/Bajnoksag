package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import com.szelev.bajnoksag.logic.Egyeneskieses;
import com.szelev.bajnoksag.R;

/**
 * Created by Levente on 2016.12.21..
 */

public class EgyeneskiesesActivity extends AppCompatActivity{

    private static Egyeneskieses logika;
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
            logika = new Egyeneskieses();
            logika.init(merkozesTabla, tovabbjutokTabla);
            logika.general();
            vanLogika = true;
        }

        merkozesTabla       = (TableLayout) (findViewById(R.id.table_main));
        tovabbjutokTabla    = (TableLayout) (findViewById(R.id.table_main2));

        bajnoksagAllapotKiir();
    }

    private void bajnoksagAllapotKiir()
    {
        logika.refreshKiiras(logika.getAktualisSzint(), this);
    }

}
