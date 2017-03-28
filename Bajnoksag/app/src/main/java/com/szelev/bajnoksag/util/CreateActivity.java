package com.szelev.bajnoksag.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by szucs on 2017.03.28..
 */

public class CreateActivity {

    public static void start(Class c, AppCompatActivity compatActivity) {
        Intent intent = new Intent(compatActivity, c);
        compatActivity.startActivity(intent);
    }
}
