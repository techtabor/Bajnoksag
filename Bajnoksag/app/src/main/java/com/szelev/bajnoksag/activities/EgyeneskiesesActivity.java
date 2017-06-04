package com.szelev.bajnoksag.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.szelev.bajnoksag.logic.Egyeneskieses;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.util.DrawTable;

/**
 * Created by Levente on 2016.12.21..
 */

public class EgyeneskiesesActivity extends AppCompatActivity{

    private static Egyeneskieses logika;
    private static boolean          vanLogika = false;

    private TableLayout merkozesTabla;
    private TableLayout tovabbjutokTabla;
    private TableLayout szintValasztoTabl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyeneskieses);

        init();
    }

    public void init()
    {
        merkozesTabla       = (TableLayout) (findViewById(R.id.table_main));
        tovabbjutokTabla    = (TableLayout) (findViewById(R.id.table_main2));
        szintValasztoTabl   = (TableLayout) (findViewById(R.id.table_main0));

        TableRow tr = DrawTable.createRowWithOneCell("Körök: ", this);

        if(!vanLogika)
        {
            szintValasztoTabl.addView(tr);
            logika = new Egyeneskieses();
            logika.init(merkozesTabla, tovabbjutokTabla, tr, this);
            logika.general();
            vanLogika = true;
        }

        bajnoksagAllapotKiir();
    }



    private void bajnoksagAllapotKiir()
    {
        logika.refreshKiiras(logika.getAktualisSzint(), this);
    }

}
