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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //TODO (szgabbor): Ez miért statikus?
    public static ArrayList<Csapat> csapatok = new ArrayList<>();
    public static int               activity_number = 0;
    private AppCompatActivity       activity;

    private Button              ujCsapatBut;
    private Button              tovabbBut;
    private EditText            csapatNevText;
    private EditText            csapatSulyText;
    private TableLayout         csapatTabla;

    private int defaultTextColor = Color.BLACK;
    private int defaultGravity = Gravity.CENTER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csapathozzaadas);

        init();
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
        if(v.equals(findViewById(R.id.buttonUjCsapat))) {
            actionOnUjCsapatButton();
        }
        if(v.equals(findViewById(R.id.button2))) {
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
        create();
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










    public void create() {
        System.out.println(activity_number);
        switch (activity_number) {
            case 0:
                CsapatHozzaAdas act = new CsapatHozzaAdas(this);
                setContentView(R.layout.activity_csapathozzaadas);
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
            case 3:
                setContentView(R.layout.activity_kiertekelesbeallitasok);
                activity = new KiertekelesBeallitasok(this);
                break;
            case 4:
                setContentView(R.layout.activity_modvalaszto);
                activity = new Modvalaszto(this);
                break;
            case 5:
                setContentView(R.layout.activity_egyeneskieses);
                activity = new Egyeneskieses(this);
                break;
            default:
                activity = null;
        }
    }
}