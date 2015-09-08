/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import woordenapplicatie.WoordenApplicatie;

/**
 *
 * @author Stijn
 */
public class WoordenControllerTest {

    public WoordenControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    String tekst;

    String teksterino = "";

    @Before
    public void setUp() {
        tekst = "Een, twee, drie, vier\n"
                + "Hoedje van, hoedje van\n"
                + "Een, twee, drie, vier\n"
                + "Hoedje van papier\n";

        for (int i = 0; i < 10000; i++) {
            teksterino += "A B C D E ";
        }

    }

    @Test
    public void aantalTest() {
        assertEquals(WoordenController.Aantal(tekst), "Totaal aantal woorden: 15\n"
                + " Aantal verschillende woorden: 7");
        assertEquals(WoordenController.Aantal(""), "Totaal aantal woorden: 1\n"
                + " Aantal verschillende woorden: 1");
        assertEquals(WoordenController.Aantal("HEY KNUL"), "Totaal aantal woorden: 2\n"
                + " Aantal verschillende woorden: 2");
        assertEquals(WoordenController.Aantal(teksterino), "Totaal aantal woorden: 50000\n"
                + " Aantal verschillende woorden: 5");
    }

    @Test
    public void frequetieTest() {
        assertEquals(WoordenController.Frequentie(tekst), "[papier=1\n"
                + ", twee=2\n"
                + ", drie=2\n"
                + ", vier=2\n"
                + ", een=2\n"
                + ", hoedje=3\n"
                + ", van=3\n"
                + "]");
        assertEquals(WoordenController.Frequentie(""), "[=1\n" +
"]");
        assertEquals(WoordenController.Frequentie("HEY KNUL"), "[knul=1\n" +
", hey=1\n" +
"]");
    }

    @Test
    public void concordantieTest() {
        assertEquals(WoordenController.Concordantie(tekst), "Een: 1 3\n"
                + "Hoedje: 2 4\n"
                + "drie: 1 3\n"
                + "hoedje: 2\n"
                + "papier: 4\n"
                + "twee: 1 3\n"
                + "van: 2 2 4\n"
                + "vier: 1 3\n");
        assertEquals(WoordenController.Concordantie(""), ": 1\n");
        assertEquals(WoordenController.Concordantie("HEY KNUL"), "HEY: 1\n" +
"KNUL: 1\n");
    }

    @Test
    public void sorteerTest() {
        assertEquals(WoordenController.Sorteer(tekst), "[vier, van, twee, papier, hoedje, een, drie]");
        assertEquals(WoordenController.Sorteer(""), "[]");
        assertEquals(WoordenController.Sorteer("HEY KNUL"), "[knul, hey]");
        assertEquals(WoordenController.Sorteer(teksterino), "[e, d, c, b, a]");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of initialize method, of class WoordenController.
     */
    @Test
    public void aantalActionText() {

    }

}
