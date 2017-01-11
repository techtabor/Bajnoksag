package com.szelev.bajnoksag.AppCompatActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.szelev.bajnoksag.Csapat;
import com.szelev.bajnoksag.Logic.CsapatHozzaadasL;
import com.szelev.bajnoksag.R;
import com.szelev.bajnoksag.Utilities;

import java.util.ArrayList;

public class CsapatHozzaadasACA extends AppCompatActivity{

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
        for(int i = 0; i < Utilities.csapatok.size(); i++) {
            csapatTabla.addView(CsapatHozzaadasL.getCsapatRowByIndex(i, this));
        }
    }

    private void resetInput()
    {
        csapatNevText.setText("");
        csapatSulyText.setText("0");
    }

    //onClik event
    public void actionOnUjCsapatButton(View v) {
        CsapatHozzaadasL.addCsapat(csapatNevText.getText().toString(), Integer.parseInt(csapatSulyText.getText().toString()));

        csapatTabla.addView(CsapatHozzaadasL.getLastCsapatRow(this));
        resetInput();
    }

    //onClick event
    public void actionOnTovabbButton(View v) {
        Utilities.startNewActivity(ModvalasztoACA.class, this);
    }
}