package com.szelev.bajnoksag.util;

import android.content.Context;

import com.szelev.bajnoksag.data.Scores;
import com.szelev.bajnoksag.data.Teams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Levente on 2017.06.05..
 */

public class SaveLoad {

    public static void loadKorm(String filename, Context c)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(c.getFilesDir().getAbsolutePath() + filename));
            Scores.loadFromString(br.readLine());
            Teams.csapatokLoadFromString(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveKorm(String filename, Context c)
    {
        try {
            File f = new File(c.getFilesDir().getAbsolutePath() + filename);
            if(!f.exists())
                f.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(c.getFilesDir().getAbsolutePath() + filename));
            bw.write(Scores.tooString());
            bw.newLine();
            bw.write(Teams.csapatokToString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
