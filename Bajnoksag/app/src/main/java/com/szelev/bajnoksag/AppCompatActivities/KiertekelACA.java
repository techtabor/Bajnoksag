package com.szelev.bajnoksag.AppCompatActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;

import com.szelev.bajnoksag.data.Csapat;
import com.szelev.bajnoksag.data.CsapatTul;
import com.szelev.bajnoksag.data.DataContainer;
import com.szelev.bajnoksag.util.DrawTable;
import com.szelev.bajnoksag.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Levente on 2016.12.18..
 */


public class KiertekelACA extends AppCompatActivity{

    private TableLayout                 ertekeloTabla;
    public  static ArrayList<CsapatTul>   sorrend;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiertekel);

        sorrendKiszamol();

        init();
    }

    public void init()
    {
        ertekeloTabla   = (TableLayout) (findViewById(R.id.table_main));

        kiirErtekeles();
    }

    private void kiirErtekeles()
    {
        ertekeloTabla.addView(DrawTable.createRowWithTwoCell("     Helyezés     ", "     Csapatnév     ", this));

        for(int i = 0; i< KiertekelACA.sorrend.size(); i++)
        {
            ertekeloTabla.addView(DrawTable.createRowWithTwoCell(Integer.toString(i+1), DataContainer.getTeam(KiertekelACA.sorrend.get(i).ID).getNev(), this));
        }
    }

    public static void sorrendKiszamol()
    {
        sorrend = new ArrayList<>();

        // kezdetben a csapatok index szerint vannak sorbarendezve

        ArrayList<Csapat> teams = DataContainer.getTeams();
        for(int i = 0; i < teams.size(); i++)
        {
            CsapatTul ct = new CsapatTul();
            ct.ID = i;
            sorrend.add(ct);
        }
        //sorbarakPontszam(0, DrawTable.csapatok.size()-1);

        for(int i = 0; i< teams.size(); i++)
        {
            for(int j = 0; j< teams.size(); j++)
            {
                if(i!=j)
                {
                    sorrend.get(i).pontszam += getPontszam(KormerkozesekACA.logika.eredmenyek.get(i).get(j).getElso(), KormerkozesekACA.logika.eredmenyek.get(i).get(j).getMasodik(), KormerkozesekACA.logika.eredmenyek.get(i).get(j).voltMeccs());
                    sorrend.get(i).pontkul  += KormerkozesekACA.logika.eredmenyek.get(i).get(j).getElso()-KormerkozesekACA.logika.eredmenyek.get(i).get(j).getMasodik();
                    sorrend.get(i).szerzettPont += KormerkozesekACA.logika.eredmenyek.get(i).get(j).getElso();
                    if(KormerkozesekACA.logika.eredmenyek.get(i).get(j).getElso()>KormerkozesekACA.logika.eredmenyek.get(i).get(j).getMasodik())
                        sorrend.get(i).gyozelemszam++;
                    if(KormerkozesekACA.logika.eredmenyek.get(i).get(j).voltMeccs())
                        sorrend.get(i).jatszottMeccsek++;
                }
            }
        }

        Collections.sort(sorrend, new Comparator<CsapatTul>() {
            @Override
            public int compare(CsapatTul o1, CsapatTul o2) {
                if(((Integer)(o1.pontszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontszam*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.pontszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontszam*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.pontkul*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontkul*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.pontkul*o2.jatszottMeccsek)).compareTo((Integer)(o2.pontkul*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.szerzettPont*o2.jatszottMeccsek)).compareTo((Integer)(o2.szerzettPont*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.szerzettPont*o2.jatszottMeccsek)).compareTo((Integer)(o2.szerzettPont*o1.jatszottMeccsek)))*(-1);
                }
                if(((Integer)(o1.gyozelemszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.gyozelemszam*o1.jatszottMeccsek)) != 0)
                {
                    return (((Integer)(o1.gyozelemszam*o2.jatszottMeccsek)).compareTo((Integer)(o2.gyozelemszam*o1.jatszottMeccsek)))*(-1);
                }
                return 0;
            }
        });


    }

    private static int getPontszam(int egyik, int masik, boolean voltMeccs)
    {
        if(!voltMeccs)
            return KiertekelesBeallitasokACA.nemVoltMegMeccsPont;
        if(egyik > masik)
            return KiertekelesBeallitasokACA.gyozelemPont;
        if(egyik < masik)
            return KiertekelesBeallitasokACA.veresegPont;
        return KiertekelesBeallitasokACA.dontetlenPont;
    }
/*
    private static void sorbarakPontszam(int kezdet, int veg)
    {
        // pontszamokat tarolo arrayList inicializalasa

        ArrayList<ArrayList<Integer>> pontszamok = new ArrayList<>();   // (veg-kezdet+1)x2-es arrayList, az elso elem az index, a masodik a pontszam
        for(int i=0; i<veg-kezdet+1; i++)
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(sorrend.get(kezdet+i));
            al.add(0);
            pontszamok.add(al);
        }

        // pontszamok kiszamitasa

        for(int i=0; i<pontszamok.size(); i++)
        {
            for(int j=0; j<pontszamok.size(); j++)
            {
                if(i!=j)
                {
                    pontszamok.get(i).set(1, pontszamok.get(i).get(1) + getPontszam(KormerkozesekACA.logika.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getElso(), KormerkozesekACA.logika.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).getMasodik(), KormerkozesekACA.logika.eredmenyek.get(pontszamok.get(i).get(0)).get(pontszamok.get(j).get(0)).voltMeccs()));
                }
            }
        }

        // pontszámok szerinti rendezés
        //TODO (szgabbor): Ez itt elég bonyolultnak tűnik nekem.
        //(szelev): ez olyan, mint C++-ban az algorithm könyvtár sort-ja
        Collections.sort(pontszamok, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return (o1.get(1).compareTo(o2.get(1)))*(-1);
            }
        });

        for(int i=0; i<pontszamok.size(); i++)
        {
            KiertekelACA.sorrend.set(kezdet+i, pontszamok.get(i).get(0));
        }

        int azonoskezdet = 0;
        for(int i=1; i<pontszamok.size(); i++) {
            if (pontszamok.get(azonoskezdet).get(1) != pontszamok.get(i).get(1)) {
                if (azonoskezdet != i - 1) {
                    sorbarakPontszam(kezdet + azonoskezdet, kezdet + i - 1);
                }
                azonoskezdet = i;
            }
        }

        if(azonoskezdet != 0 && azonoskezdet != pontszamok.size()-1) {
            sorbarakPontszam(kezdet + azonoskezdet, kezdet + pontszamok.size() - 1);
        } else if(azonoskezdet == 0)
        {
            sorbarakEredmenykulonbseg(kezdet, veg);
        }


    }

    private static void sorbarakEredmenykulonbseg(int kezdet, int veg)
    {
        // eredmenykulonbsegeket tarolo arrayList inicializalasa

        ArrayList<ArrayList<Integer>> eredmenyk = new ArrayList<>();   // (veg-kezdet+1)x2-es arrayList, az elso elem az index, a masodik a pontszam
        for(int i=0; i<veg-kezdet+1; i++)
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(sorrend.get(kezdet+i));
            al.add(0);
            eredmenyk.add(al);
        }

        // eredmenykulonbsegek kiszamitasa

        for(int i=0; i<eredmenyk.size(); i++)
        {
            for(int j=0; j<sorrend.size(); j++)
            {
                if(eredmenyk.get(i).get(0)!=sorrend.get(j))
                {
                    if (KormerkozesekACA.logika.eredmenyek.get(eredmenyk.get(i).get(0)).get(sorrend.get(j)).voltMeccs())
                    {
                        eredmenyk.get(i).set(1,eredmenyk.get(i).get(1)+ KormerkozesekACA.logika.eredmenyek.get(eredmenyk.get(i).get(0)).get(sorrend.get(j)).getElso()- KormerkozesekACA.logika.eredmenyek.get(eredmenyk.get(i).get(0)).get(sorrend.get(j)).getMasodik());
                    }
                }
            }
        }

        // eredmenykulsonbsegek szerinti rendezés

        Collections.sort(eredmenyk, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return (o1.get(1).compareTo(o2.get(1)))*(-1);
            }
        });

        for(int i=0; i<eredmenyk.size(); i++)
        {
            KiertekelACA.sorrend.set(kezdet+i, eredmenyk.get(i).get(0));
        }


        int azonoskezdet = 0;
        for(int i=1; i<eredmenyk.size(); i++) {
            if (eredmenyk.get(azonoskezdet).get(1) != eredmenyk.get(i).get(1)) {
                if (azonoskezdet != i - 1) {
                    sorbarakPontszam(kezdet + azonoskezdet, kezdet + i - 1);
                }
                azonoskezdet = i;
            }
        }

        if(azonoskezdet != 0 && azonoskezdet != eredmenyk.size()-1) {
            sorbarakPontszam(kezdet + azonoskezdet, kezdet + eredmenyk.size() - 1);
        } else if(azonoskezdet == 0)
        {
            sorbarakSzerzettPontok(kezdet, veg);
        }

    }

    private static void sorbarakSzerzettPontok(int kezdet, int veg)
    {
        // szerzett pontokat tarolo arrayList inicializalasa

        ArrayList<ArrayList<Integer>> szerzettp = new ArrayList<>();   // (veg-kezdet+1)x2-es arrayList, az elso elem az index, a masodik a pontszam
        for(int i=0; i<veg-kezdet+1; i++)
        {
            ArrayList<Integer> al = new ArrayList<>();
            al.add(sorrend.get(kezdet+i));
            al.add(0);
            szerzettp.add(al);
        }

        // eredmenykulonbsegek kiszamitasa

        for(int i=0; i<szerzettp.size(); i++)
        {
            for(int j=0; j<sorrend.size(); j++)
            {
                if(szerzettp.get(i).get(0)!=sorrend.get(j))
                {
                    if (KormerkozesekACA.logika.eredmenyek.get(szerzettp.get(i).get(0)).get(sorrend.get(j)).voltMeccs())
                    {
                        szerzettp.get(i).set(1,szerzettp.get(i).get(1)+ KormerkozesekACA.logika.eredmenyek.get(szerzettp.get(i).get(0)).get(sorrend.get(j)).getElso());
                    }
                }
            }
        }

        // eredmenykulsonbsegek szerinti rendezés

        Collections.sort(szerzettp, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return (o1.get(1).compareTo(o2.get(1)))*(-1);
            }
        });

        for(int i=0; i<szerzettp.size(); i++)
        {
            KiertekelACA.sorrend.set(kezdet+i, szerzettp.get(i).get(0));
        }


        int azonoskezdet = 0;
        for(int i=1; i<szerzettp.size(); i++) {
            if (szerzettp.get(azonoskezdet).get(1) != szerzettp.get(i).get(1)) {
                if (azonoskezdet != i - 1) {
                    sorbarakPontszam(kezdet + azonoskezdet, kezdet + i - 1);
                }
                azonoskezdet = i;
            }
        }

        if(azonoskezdet != 0 && azonoskezdet != szerzettp.size()-1) {
            sorbarakPontszam(kezdet + azonoskezdet, kezdet + szerzettp.size() - 1);
        }

    }
*/
    //onClick event
    public void actionOnVisszaButton(View v)
    {
        DrawTable.startNewActivity(KormerkozesekACA.class, this);
    }

    //onClick event
    public void actionOnTovabbButton(View v)
    {
        DrawTable.startNewActivity(KiertekelesBeallitasokACA.class, this);
    }

}