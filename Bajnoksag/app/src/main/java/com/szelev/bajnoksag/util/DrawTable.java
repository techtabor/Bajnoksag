package com.szelev.bajnoksag.util;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import com.szelev.bajnoksag.data.Csapat;

import java.util.ArrayList;

/**
 * Created by Levente on 2016.12.28..
 */

public class DrawTable {

    public static ArrayList<Csapat> csapatok = new ArrayList<>();

    public static int defaultTextColor  = Color.BLACK;
    public static int defaultGravity    = Gravity.CENTER;

    public static TableRow createRowWithOneCell(String cell, AppCompatActivity ACA)
    {
        TableRow result = new TableRow(ACA);
        TextView textView = createTextView(cell, ACA);
        result.addView(textView);
        return result;
    }

    public static TableRow createRowWithTwoCell(String firstCell, String secondCell, AppCompatActivity compatActivity)
    {
        TableRow result = new TableRow(compatActivity);
        TextView firstTextView = createTextView(firstCell, compatActivity);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell, compatActivity);
        result.addView(secondTextView);
        return result;
    }

    public static TableRow createRowWithThreeCell(String firstCell, String secondCell, String thirdCell, AppCompatActivity compatActivity)
    {
        TableRow result = new TableRow(compatActivity);
        TextView firstTextView = createTextView(firstCell, compatActivity);
        result.addView(firstTextView);
        TextView secondTextView = createTextView(secondCell, compatActivity);
        result.addView(secondTextView);
        TextView thirdTextView = createTextView(thirdCell, compatActivity);
        result.addView(thirdTextView);
        return result;
    }

    public static TextView createTextView(String text, AppCompatActivity compatActivity, int minWidth) {
        TextView textView = new TextView(compatActivity);
        textView.setText(text);
        textView.setTextColor(defaultTextColor);
        textView.setGravity(defaultGravity);
        textView.setMinWidth(minWidth);
        return textView;
    }

    public static TextView createTextView(String text, AppCompatActivity compatActivity)
    {
        return createTextView(text, compatActivity, 0);
    }

    public static void startNewActivity(Class c, AppCompatActivity compatActivity)
    {
        Intent intent = new Intent(compatActivity, c);

        compatActivity.startActivity(intent);
    }

}