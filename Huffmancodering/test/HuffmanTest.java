/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import huffmancodering.Huffman;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bas
 */
public class HuffmanTest {

    private huffmancodering.Huffman hf;

    public HuffmanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    Map hfmap1;
    Map hfmap2;
    Map hfmap3;

    Map hfResult1;
    Map hfResult2;
    Map hfResult3;

    @Before
    public void setUp() {
        hf = new Huffman();
        hfmap1 = hf.countCharacters("Hallo");
        hfmap2 = hf.countCharacters("Banaan");
        hfmap3 = hf.countCharacters("Lorem Ipsum Dolar ETC");

        hfResult1 = new HashMap();
        hfResult2 = new HashMap();
        hfResult3 = new HashMap();

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCountCharacters() {
        
        hfResult1.put("H", 1);
        hfResult1.put("a", 1);
        hfResult1.put("l", 2);
        hfResult1.put("o", 1);

        hfResult2.put("B", 1);
        hfResult2.put("a", 3);
        hfResult2.put("n", 2);

        hfResult3.put("L", 1);
        hfResult3.put("o", 2);
        hfResult3.put("r", 2);
        hfResult3.put("e", 1);
        hfResult3.put("m", 2);
        hfResult3.put(" ", 3);
        hfResult3.put("I", 1);
        hfResult3.put("p", 1);
        hfResult3.put("s", 1);
        hfResult3.put("u", 1);
        hfResult3.put("m", 1);
        hfResult3.put("D", 1);
        hfResult3.put("l", 1);
        hfResult3.put("a", 1);
        hfResult3.put("E", 1);
        hfResult3.put("T", 1);
        hfResult3.put("C", 1);

        assertEquals("map1 niet gelijk", hfmap1, hfResult1);
        assertEquals("map2 niet gelijk", hfmap2, hfResult2);
        assertEquals("map3 niet gelijk", hfmap1, hfResult1);
    }

    @Test
    public void testSortMap() {
        hfmap1 = hf.sortMapOnFreq(hfmap1);
        hfmap2 = hf.sortMapOnFreq(hfmap2);
        hfmap3 = hf.sortMapOnFreq(hfmap3);


        hfResult1.put('H', 1);
        hfResult1.put('a', 1);
        hfResult1.put('o', 1);
        hfResult1.put('l', 2);

        hfResult2.put('B', 1);
        hfResult2.put('a', 3);
        hfResult2.put('n', 2);

        hfResult3.put('L', 1);
        hfResult3.put('o', 2);
        hfResult3.put('r', 2);
        hfResult3.put('e', 1);
        hfResult3.put('m', 2);
        hfResult3.put(' ', 3);
        hfResult3.put('I', 1);
        hfResult3.put('p', 1);
        hfResult3.put('s', 1);
        hfResult3.put('u', 1);
        hfResult3.put('m', 1);
        hfResult3.put('D', 1);
        hfResult3.put('l', 1);
        hfResult3.put('a', 1);
        hfResult3.put('E', 1);
        hfResult3.put('T', 1);
        hfResult3.put('C', 1);

        
        assertEquals("map1 niet gelijk", hfmap1, hfResult1);
        assertEquals("map2 niet gelijk", hfmap2, hfResult2);
        assertEquals("map3 niet gelijk", hfmap1, hfResult1);
    }
    
    @Test
    public void testHuffmanboom()
    {
        
        hfmap1 = hf.sortMapOnFreq(hfmap1);
        hfmap2 = hf.sortMapOnFreq(hfmap2);
        hfmap3 = hf.sortMapOnFreq(hfmap3);
        hfmap1 = hf.createTree(hfmap1);
        hfmap2 = hf.createTree(hfmap2);
        hfmap3 = hf.createTree(hfmap3);
        hfResult1.put('a', "110");
        hfResult1.put('H', "10");
        hfResult1.put('l', "0");
        hfResult1.put('o', "111");

        hfResult2.put('B', "10");
        hfResult2.put('a', "0");
        hfResult2.put('n', "11");

        hfResult3.put(' ', "101");
        hfResult3.put('a', "11000");
        hfResult3.put('C', "0111");
        hfResult3.put('D', "11001");
        hfResult3.put('e', "0010");
        hfResult3.put('E', "1001");
        hfResult3.put('I', "0011");
        hfResult3.put('l', "1000");
        hfResult3.put('L', "11101");
        hfResult3.put('m', "000");
        hfResult3.put('o', "010");
        hfResult3.put('p', "0110");
        hfResult3.put('r', "1111");
        hfResult3.put('s', "11010");
        hfResult3.put('T', "11100");
        hfResult3.put('u', "11011");
        
        assertEquals("map1 niet gelijk", hfmap1, hfResult1);
        assertEquals("map2 niet gelijk", hfmap2, hfResult2);
        assertEquals("map3 niet gelijk", hfmap1, hfResult1);
    }
}
