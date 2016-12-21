package com.szelev.bajnoksag.ACAs;

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

import java.util.ArrayList;

public class ACACsapatHozzaadas extends AppCompatActivity{

    public static ArrayList<Csapat> csapatok = new ArrayList<>();

    private EditText            csapatNevText;
    private EditText            csapatSulyText;
    private TableLayout         csapatTabla;

    private int defaultTextColor    = Color.BLACK;
    private int defaultGravity      = Gravity.CENTER;


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
        TableRow header = createRowWithTwoCell("     Csapatnév     ", "     Csapatsúly     ");
        csapatTabla.addView(header);
        for(int i = 0; i < ACACsapatHozzaadas.csapatok.size(); i++) {
            Csapat c = ACACsapatHozzaadas.csapatok.get(i);

            TableRow row = createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ");
            csapatTabla.addView(row);
        }
    }

    //onClik event
    public void actionOnUjCsapatButton(View v) {
        Csapat c = new Csapat();

        c.setID(ACACsapatHozzaadas.csapatok.size());
        c.setNev(csapatNevText.getText().toString());
        c.setSuly(csapatSulyText.getText().toString());

        ACACsapatHozzaadas.csapatok.add(c);
        TableRow row = createRowWithTwoCell(" " + c.getNev() + " ", " " + c.getSuly() + " ");

        csapatTabla.addView(row);

        csapatNevText.setText("");
        csapatSulyText.setText("0");
    }

    //onClick event
    public void actionOnTovabbButton(View v) {
        Intent intent = new Intent(this, ACAModvalaszto.class);

        startActivity(intent);
    }

    //TODO (szgabbor): Ezt máshol is használhatod, érdemes lehet új osztályba kiszervezni.
    private TableRow createRowWithTwoCell(String firstCell, String secondCell) {
        TableRow result = new TableRow(this);
        TextView firstTextView = createTextView(firstCell);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell);
        result.addView(secondTextView);
        return result;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(defaultTextColor);
        textView.setGravity(defaultGravity);
        return textView;
    }
}