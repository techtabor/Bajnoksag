package com.szelev.bajnoksag.AppCompatActivities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;

public class CsapatHozzaadasACA extends AppCompatActivity{

    public static ArrayList<Csapat> csapatok = new ArrayList<>();

    private EditText            csapatNevText;
    private EditText            csapatSulyText;
    private TableLayout         csapatTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csapathozzaadas);

        init();
    }

    public void init() {

        csapatNevText   = (EditText)    (findViewById(R.id.editTextCsapatNev));
        csapatSulyText  = (EditText)    (findViewById(R.id.editTextCsapatSuly));
        csapatTabla     = (TableLayout) (findViewById(R.id.table_main));


        createTable();
    }

    private void createTable() {
        TableRow header = Utilities.createRowWithTwoCell("     Csapatnév     ", "     Csapatsúly     ", this);
        csapatTabla.addView(header);
        for(int i = 0; i < CsapatHozzaadasACA.csapatok.size(); i++) {
            Csapat c = CsapatHozzaadasACA.csapatok.get(i);

            TableRow row = Utilities.createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ", this);
            csapatTabla.addView(row);
        }
    }

    //onClik event
    public void actionOnUjCsapatButton(View v) {
        Csapat c = new Csapat(CsapatHozzaadasACA.csapatok.size(), csapatNevText.getText().toString(), Integer.parseInt(csapatSulyText.getText().toString()));

        CsapatHozzaadasACA.csapatok.add(c);
        TableRow row = Utilities.createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ", this);

        csapatTabla.addView(row);

        csapatNevText.setText("");
        csapatSulyText.setText("0");
    }

    //onClick event
    public void actionOnTovabbButton(View v) {
        Utilities.startNewActivity(ModvalasztoACA.class, this);
    }
}