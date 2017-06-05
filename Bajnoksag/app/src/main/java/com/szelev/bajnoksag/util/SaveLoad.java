package com.szelev.bajnoksag.util;

import com.szelev.bajnoksag.data.Teams;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Levente on 2017.06.05..
 */

public class SaveLoad {

    public void load(String filename)
    {

    }

    public void save(String filename, int mode)                                     // mode : 0->korm, 1->egykies
    {
        File f = new File(filename);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(f, true), 1024);









        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
