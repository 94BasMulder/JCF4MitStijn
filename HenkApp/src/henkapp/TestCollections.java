/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package henkapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import javafx.application.Application;
import static javafx.application.Application.launch;

/**
 *
 * @author Stijn
 */
public class TestCollections {

    //Minimum 10
    public static int aantal_personen = 1;

    public static void main(String[] args) {
        aantal_personen = aantal_personen < 10 ? 10 : aantal_personen;
        Random rand = new Random();
        List<String> plaatsen = new ArrayList<>(aantal_personen / 10);
        for (int i = 0; i < aantal_personen / 10; i++) {
            plaatsen.add(UUID.randomUUID().toString());
        }
        List<Persoon> mensenArray = new ArrayList<>(aantal_personen);
        for (int i = 0; i < aantal_personen; i++) {
            mensenArray.add(new Persoon(UUID.randomUUID().toString(), plaatsen.get((int) (Math.random() * aantal_personen / 10)), UUID.randomUUID().toString()));
        }

        Set TS = new TreeSet();
        Map TM = new TreeMap();
        Map HM = new HashMap();

        for (Persoon p : mensenArray) {
            TS.add(p);
            TM.put(p, p.getNaam());
            HM.put(p, p.getNaam());
        }
        System.out.println("HashMap:");
        for (int i = 0; i < HM.size(); i++) {
            System.out.println(i+1 + ": " + HM.get(mensenArray.get(i)));
        }
        System.out.println("");
        System.out.println("TreeMap:");
        for (int i = 0; i < TM.size(); i++) {
            System.out.println(i+1 + ": " + TM.get(mensenArray.get(i)));
        }
    }
}
