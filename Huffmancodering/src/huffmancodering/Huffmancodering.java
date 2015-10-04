/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Bas
 */
public class Huffmancodering {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* String output = "";
         while(!output.equals("Exit"))
         {
            
        
         }
         */
        Huffman hf = new Huffman();
       System.out.print(hf.createTree((HashMap) hf.sortHashMapOnFrequency(hf.countCharacters("AaBadeasFesAHASDgassgasfhsdUJJdgjuryghdfdeet"))));
    }

    
    
}
