package com.szelev.bajnoksag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO (szgabbor): Ez miért statikus?
    public static ArrayList<Csapat> csapatok = new ArrayList<>();
    public static int               activity_number = 0;
    private AppCompatActivity       activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        create();
    }

    public void create() {
        System.out.println(activity_number);
        switch (activity_number) {
            case 0:
                setContentView(R.layout.activity_main);
                CsapatHozzaAdas act = new CsapatHozzaAdas(this);
                act.init();
                activity = act;
                break;
            case 1:
                setContentView(R.layout.activity_csoportmerkozesek);
                activity = new Kormerkozesek(this);
                break;
            case 2:
                setContentView(R.layout.activity_kiertekel);
                activity = new Kiertekel(this);
                break;
            default:
                activity = null;
        }
    }
}