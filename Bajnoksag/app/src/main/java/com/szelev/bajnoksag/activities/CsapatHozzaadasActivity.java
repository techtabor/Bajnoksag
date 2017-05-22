package com.szelev.bajnoksag.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.szelev.bajnoksag.data.Teams;
import com.szelev.bajnoksag.util.CreateActivity;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.logic.CsapatHozzaadas;
import com.szelev.bajnoksag.R;

public class CsapatHozzaadasActivity extends AppCompatActivity{

    private CsapatHozzaadas logika = new CsapatHozzaadas();;

    private EditText            csapatNevText;
    private TableLayout         csapatTabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csapathozzaadas);
        init();
    }

    public void init() {
        csapatNevText   = (EditText)    (findViewById(R.id.editTextCsapatNev));
        csapatTabla     = (TableLayout) (findViewById(R.id.table_main));

        drawTable();
    }

    private void drawTable() {
        csapatTabla.removeAllViews();

        TableRow header = DrawTable.createRowWithThreeCell(" ", "     Csapatok     ", " ", this);
        csapatTabla.addView(header);

        for(int i = 0; i < (Teams.getTeams().size()+2)/3; i++) {
            csapatTabla.addView(logika.getCsapatRowByIndex(i, this));
        }
    }

    private void resetInput()
    {
        csapatNevText.setText("");
    }

    //onClik event
    public void actionOnUjCsapatButton(View v) {
        logika.addCsapat(csapatNevText.getText().toString());
        drawTable();

        resetInput();
    }

    //onClick event
    public void actionOnTovabbButton(View v) {
        CreateActivity.start(ModvalasztoActivity.class, this);
    }
}
