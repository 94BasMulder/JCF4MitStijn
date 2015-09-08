package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static java.lang.System.in;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
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
        Collection henk = new ArrayList<>();
        String text = taInput.getText().toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) {
            henk.add(s.trim()); // weghalen onnodige spaties en toevoegen aan de lijst
        };
        Set hashhenk = new HashSet<String>(henk); //omzetten naar HashSet om unieke resultaten te krijgen
        String out = "Totaal aantal woorden: " + henk.size() + "\n Aantal verschillende woorden: " + hashhenk.size();
        taOutput.setText(out);
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        Collection henk = new HashSet<>();
        String text = taInput.getText().toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) {
            henk.add(s.trim()); // weghalen onnodige spaties en toevoegen aan de lijst
        };
        Set hashhenk = new TreeSet<String>(Collections.reverseOrder()); //Aanmaken van een gesorteerde treeset die DESC sorteert
        hashhenk.addAll(henk);// Het toevoegen van alle items.
        taOutput.setText(hashhenk + "\n");
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        Map henkmap = new HashMap();
        Map henklinkmap = new LinkedHashMap();
        Collection henklist = new ArrayList<>();
        String text = taInput.getText().toLowerCase().replace("\r", " ").replace(",", "").replace("\n", " "); // Weghalen van comma's en enters
        for (String s : text.split(" ")) {
            if (henkmap.containsKey(s)) {
                henkmap.replace(s, (int) henkmap.get(s) + 1);
            } else {
                henkmap.put(s, 1);
            }
        };
        henklinkmap.putAll(henkmap);
        
        String output = "";
        henklinkmap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach((entry) -> henklist.add(entry.toString()+"\n") ) ;
        //taOutput.setText(output);
        taOutput.setText(henklist.toString());
    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        //Aanmaken van de map gekozen voor een treemap vanwege automatisch op volgorde zetten.
        Map henkmap = new TreeMap();
        //aanmaken van een int voor de referentie.
        int i = 1;
        //lijn ophalen om te kijken waar de woorden staan.
        for (String line : taInput.getText().split("\n")) {
            //woord voor woord gaan kijken of dat het bestaat in de treemap. zo niet voeg het toe aan de map, anders updaten van de value
            for (String word : line.split(" ")) {
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
        for (Object s : henkmap.keySet()) {
            output += s + ": " + henkmap.get(s) + "\n";
        }
        taOutput.setText(output);

    }

}
