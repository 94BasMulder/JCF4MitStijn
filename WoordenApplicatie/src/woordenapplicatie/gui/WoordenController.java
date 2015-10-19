package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        taOutput.setText(Aantal(taInput.getText()));
    }

    public static String Aantal(String tekst) {
        Collection henk = new ArrayList<>();
        String text = tekst.toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) { //Constant ( N )
            henk.add(s.trim()); // weghalen onnodige spaties en toevoegen aan de lijst
        }
        Set hashhenk = new HashSet<>(henk); //omzetten naar HashSet om unieke resultaten te krijgen
        String out = "Totaal aantal woorden: " + henk.size() + "\n Aantal verschillende woorden: " + hashhenk.size();
        return out;
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        taOutput.setText(Sorteer(taInput.getText()));
    }

    public static String Sorteer(String tekst) {
        Collection henk = new HashSet<>();
        String text = tekst.toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) { //Constant ( N )
            henk.add(s.trim()); // weghalen onnodige spaties en toevoegen aan de lijst
        }
        Set hashhenk = new TreeSet<>(Collections.reverseOrder()); //Aanmaken van een gesorteerde treeset die DESC sorteert
        hashhenk.addAll(henk);// Het toevoegen van alle items.
        return hashhenk + "";
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        taOutput.setText(Frequentie(taInput.getText()));
    }
    public static String Frequentie(String tekst) {
        Map henkmap = new HashMap(); //Unieke waarden voor frquentie analyse
        Collection henklist = new ArrayList<>(); //Houd index en maakt het mogelijk om doorheen te lopen.
        String text = tekst.toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) { //Constant ( N )
            if (henkmap.containsKey(s)) {
                henkmap.replace(s, (int) henkmap.get(s) + 1); //Aanpassen van de waarde van een element
            } else {
                henkmap.put(s, 1); //Ininteren van een element
            }
        }

        String output = "";
        henkmap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> henklist.add(entry.toString() + "\n")); //Sorteren map op value
        return henklist.toString();
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        
        taOutput.setText(Concordantie(taInput.getText()));
    }
    public static String Concordantie(String tekst) {
         //Aanmaken van de map gekozen voor een treemap vanwege automatisch op volgorde zetten.
        Map henkmap = new TreeMap();
        //aanmaken van een int voor de referentie.
        int i = 1;
        //lijn ophalen om te kijken waar de woorden staan.
        for (String line : tekst.split("\n")) { //Constant ( N )
            //woord voor woord gaan kijken of dat het bestaat in de treemap. zo niet voeg het toe aan de map, anders updaten van de value
            for (String word : line.split(" ")) { //Constant ( N )
                word = word.replaceAll(",| ", "");
                if (henkmap.containsKey(word)) {
                    henkmap.replace(word, henkmap.get(word) + " " + String.valueOf(i));
                } else {
                    henkmap.put(word, String.valueOf(i));
                }
            }
            //naar de volgende regel
            i++;
        }
        //omzetten naar text voor de TextArea.
        String output = "";
        for (Object s : henkmap.keySet()) { //Constant ( N )
            output += s + ": " + henkmap.get(s) + "\n";
        }
        return output;
     }
}
