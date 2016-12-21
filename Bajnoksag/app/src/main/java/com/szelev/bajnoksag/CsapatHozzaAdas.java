package com.szelev.bajnoksag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Levente on 2016.12.06..
 */

public class CsapatHozzaAdas extends AppCompatActivity implements View.OnClickListener{

    private Button              ujCsapatBut;
    private Button              tovabbBut;
    private EditText            csapatNevText;
    private EditText            csapatSulyText;
    private TableLayout         csapatTabla;

    private MainActivity        mainAct;

    private int defaultTextColor = Color.BLACK;
    private int defaultGravity = Gravity.CENTER;

    public CsapatHozzaAdas(MainActivity mainAct) {
        this.mainAct = mainAct;
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle)
    {
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_main);
    }

    public void init() {

        ujCsapatBut     = (Button)      (findViewById(R.id.buttonUjCsapat));
        tovabbBut       = (Button)      (findViewById(R.id.button2));
        csapatNevText   = (EditText)    (findViewById(R.id.editTextCsapatNev));
        csapatSulyText  = (EditText)    (findViewById(R.id.editTextCsapatSuly));
        csapatTabla     = (TableLayout) (findViewById(R.id.table_main));


        createTable();
        ujCsapatBut.setOnClickListener(this);
        tovabbBut.setOnClickListener(this);
    }

    private void createTable() {
        TableRow header = createRowWithTwoCell("     Csapatnév     ", "     Csapatsúly     ");
        csapatTabla.addView(header);
        for(int i = 0; i < MainActivity.csapatok.size(); i++) {
            Csapat c = MainActivity.csapatok.get(i);

            TableRow row = createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ");
            csapatTabla.addView(row);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mainAct.findViewById(R.id.buttonUjCsapat))) {
            actionOnUjCsapatButton();
        }
        if(v.equals(mainAct.findViewById(R.id.button2))) {
            actionOnTovabbButton();
        }
    }

    private void actionOnUjCsapatButton() {
        Csapat c = new Csapat();

        c.setID(MainActivity.csapatok.size());
        c.setNev(csapatNevText.getText().toString());
        c.setSuly(csapatSulyText.getText().toString());

        MainActivity.csapatok.add(c);
        TableRow row = createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ");

        csapatTabla.addView(row);

        csapatNevText.setText("");
        csapatSulyText.setText("0");
    }

    private void actionOnTovabbButton() {
        MainActivity.activity_number = 4;
        mainAct.create();
    }

    //TODO (szgabbor): Ezt máshol is használhatod, érdemes lehet új osztályba kiszervezni.
    private TableRow createRowWithTwoCell(String firstCell, String secondCell) {
        TableRow result = new TableRow(mainAct);
        TextView firstTextView = createTextView(firstCell);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell);
        result.addView(secondTextView);
        return result;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(mainAct);
        textView.setText(text);
        textView.setTextColor(defaultTextColor);
        textView.setGravity(defaultGravity);
        return textView;
    }
}
