package com.szelev.bajnoksag.logic;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.Merkozes;
import com.szelev.bajnoksag.data.Teams;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Levente on 2017.05.19..
 */

public class EgyeneskiesesSzint{

    private ArrayList<Csapat> tovabbjutok;
    private ArrayList<Merkozes> merkozesek;
    private Csapat biztosanTovabbjut = null;
    private int leNemJatszott;

    public EgyeneskiesesSzint()
    {

    }

    public String toString()
    {
        String s = "";

        s += tovabbjutok.size() + "\t";
        s += merkozesek.size() + "\t";
        s += leNemJatszott + "\t";

        for(int i=0; i<tovabbjutok.size(); i++)
        {
            s += tovabbjutok.get(i).toString() + "\t";
        }

        for(int i=0; i<merkozesek.size(); i++)
        {
            s += merkozesek.get(i).toString() + "\t";
        }

        return s;
    }

    public void loadFromString(String s)
    {
        System.err.println("EgyeneskiesesSzint.loadFromString(String s): Not supported yet.");
        String[] splitted = s.trim().split("\\t+");
        int a, b;
        a = Integer.parseInt(splitted[0]);
        b = Integer.parseInt(splitted[1]);
        leNemJatszott = Integer.parseInt(splitted[2]);
        tovabbjutok.clear();
        merkozesek.clear();
        for(int i=3; i<a+3; i++)
        {
            Csapat c = new Csapat();
            c.loadFromString(splitted[i]);
            tovabbjutok.add(c);
        }
        for(int i=a+3; i<a+b+3; i++)
        {
            Merkozes m = new Merkozes();
            m.loadFromString(splitted[i]);
            merkozesek.add(m);
        }
    }

    public void init()
    {
        tovabbjutok = new ArrayList<>();
        merkozesek = new ArrayList<>();
        leNemJatszott = 0;
    }

    public void general()
    {
        int meret = Teams.numOfTovabbjuto();

        for(int i=0; i<meret/2; i++)
        {
            Csapat cs1 = getRandomCsapatAndRemove();
            Csapat cs2 = getRandomCsapatAndRemove();
            merkozesek.add(new Merkozes(cs1, cs2, i));
        }

        if(Teams.numOfTovabbjuto() > 0)
        {
            biztosanTovabbjut = Teams.getTovabbjuto(0);
            tovabbjutok.add(biztosanTovabbjut);
        }

        leNemJatszott = merkozesek.size();
    }

    private Csapat getRandomCsapatAndRemove()
    {
        Random ran = new Random();
        int index = ran.nextInt(Teams.numOfTovabbjuto());
        Csapat c = Teams.getTovabbjuto(index);
        Teams.getTovabbjutok().remove(index);
        return c;
    }

    public  void decreaseLeNemJatszott()
    {
        leNemJatszott--;
    }

    public int getLeNemJatszott() {
        return leNemJatszott;
    }

    public Merkozes getMerkozes(int ind)
    {
        return merkozesek.get(ind);
    }

    public int getNumOfMerkozes() {
        return merkozesek.size();
    }

    public ArrayList<Csapat> getTovabbjutok() {
        return tovabbjutok;
    }
}