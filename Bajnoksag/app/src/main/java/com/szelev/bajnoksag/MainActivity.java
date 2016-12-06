package com.szelev.bajnoksag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

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
                activity = new CsapatHozzaAdas(this);
                break;
            case 1:
                
                break;
            default:
                activity = null;
        }
    }

}