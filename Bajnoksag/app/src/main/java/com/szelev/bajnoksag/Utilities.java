package com.szelev.bajnoksag;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Levente on 2016.12.28..
 */

public class Utilities {

    public static ArrayList<Csapat> csapatok = new ArrayList<>();

    public static int defaultTextColor  = Color.BLACK;
    public static int defaultGravity    = Gravity.CENTER;

    public static TableRow createRowWithOneCell(String cell, AppCompatActivity ACA)
    {
        TableRow result = new TableRow(ACA);
        TextView tv = createTextView(cell, ACA);
        result.addView(tv);
        return result;
    }

    public static TableRow createRowWithTwoCell(String firstCell, String secondCell, AppCompatActivity ACA)
    {
        TableRow result = new TableRow(ACA);
        TextView firstTextView = createTextView(firstCell, ACA);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell, ACA);
        result.addView(secondTextView);
        return result;
    }

    public static TableRow createRowWithThreeCell(String firstCell, String secondCell, String thirdCell, AppCompatActivity ACA)
    {
        TableRow result = new TableRow(ACA);
        TextView firstTextView = createTextView(firstCell, ACA);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell, ACA);
        result.addView(secondTextView);
        TextView thirdTextView = createTextView(thirdCell, ACA);
        result.addView(thirdTextView);
        return result;
    }

    public static TextView createTextView(String text, AppCompatActivity ACA, int minWidth) {
        TextView textView = new TextView(ACA);
        textView.setText(text);
        textView.setTextColor(defaultTextColor);
        textView.setGravity(defaultGravity);
        textView.setMinWidth(minWidth);
        return textView;
    }

    public static TextView createTextView(String text, AppCompatActivity ACA)
    {
        return createTextView(text, ACA, 0);
    }

    public static void startNewActivity(Class c, AppCompatActivity ACA)
    {
        Intent intent = new Intent(ACA, c);

        ACA.startActivity(intent);
    }

}