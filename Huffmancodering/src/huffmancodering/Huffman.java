/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author Bas
 */
public class Huffman {

    //huffmanboom
    public HashMap<HuffmanNode, Integer> huffmanMap = new HashMap<HuffmanNode, Integer>();

    /**
     * class om nodes van de huffmanboom op te slaan
     */
    private class HuffmanNode {

        char ch;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(char ch, int frequency, HuffmanNode left, HuffmanNode right) {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * class om de huffmannodes te vergelijken
     */
    private class HuffManComparator implements Comparator<HuffmanNode> {

        @Override
        public int compare(HuffmanNode node1, HuffmanNode node2) {
            return node1.frequency - node2.frequency;
        }
    }

    /**
     * Telt hoevaak een karakter voorkomt in de string
     * @param characters string die moet worden gelezen
     * @return de karakters en het aantal
     */
    public Map countCharacters(String characters) {
        HashMap henkMap = new HashMap();
        int prevn = 0;
        String character;
        //N
        for (int i = 1; i <= characters.length(); i++) {
            character = characters.substring(prevn, i);
            if (henkMap.containsKey(character)) {
                henkMap.put(character, (int) henkMap.get(character) + 1);
            } else {
                henkMap.put(character, 1);
            }
            prevn = i;
        }

        return henkMap;
    }

    /**
     * Sorteert de map op frequency
     * @param henkMap map die moet worden gesorteerd
     * @return gesorteerde map
     */
    public Map sortMapOnFreq(Map henkMap) {
        ArrayList<String> henklist = new ArrayList<String>();
        //N
        henkMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> henklist.add(entry.toString())); //Sorteren map op value
        Collections.sort(henklist);
        Map sortedMap = new HashMap();
        //N
        for (String item : henklist) {
            String[] items = item.split("=");
            sortedMap.put(item.charAt(0), Integer.valueOf(items[1]));
        }
        return sortedMap;
    }

    private HuffmanNode getRoot(Map<Character, Integer> henkMap) {
        PriorityQueue<HuffmanNode> henkQ = new PriorityQueue<HuffmanNode>(henkMap.size(), new HuffManComparator());
        //N
        for (Entry<Character, Integer> entry : henkMap.entrySet()) {
            henkQ.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }
        //log(N)
        while (henkQ.size() > 1) {
            HuffmanNode hn1 = (HuffmanNode) henkQ.remove();
            HuffmanNode hn2 = (HuffmanNode) henkQ.remove();
            HuffmanNode newNode = new HuffmanNode('*', hn1.frequency + hn2.frequency, hn1, hn2);
            henkQ.add(newNode);
        }
        return henkQ.remove();
    }

    /**
     * createert de code op basis van de frequentie
     * @param henkMap map met frequenties
     * @return gegenereerde code
     */
    public Map<Character, String> createTree(Map henkMap) {
        return generateCode(henkMap.keySet(), getRoot(henkMap));
    }

    /**
     * maakt de code van huffman
     * @param keySet keys van huffman
     * @param root begin punt
     * @return code
     */
    private Map<Character, String> generateCode(Set keySet, HuffmanNode root) {
        Map<Character, String> henkMap = new HashMap<Character, String>();
        if(keySet.size() <= 1)
        {            
            createNodeCode(root, henkMap, "0");
            return henkMap;
        }
        createNodeCode(root, henkMap, "");
        return henkMap;
    }

    /**
     * voegt een node toe aan de map
     * @param node map om toe te voegen
     * @param map map waaraan het toegevoegt moet worden
     * @param s code om toetevoegen
     */
    private void createNodeCode(HuffmanNode node, Map<Character, String> map, String s) {
        if (node.left == null && node.right == null) {
            map.put(node.ch, s);
            return;
        }
        createNodeCode(node.left, map, s + '0');
        createNodeCode(node.right, map, s + '1');
    }

    /**
     * vervormt de string naar de code
     * @param Message string die gecodeert moet worden
     * @return gecodeerde string
     */
    public String Codeer(String Message) {
        Map map = this.countCharacters(Message);
        Map sortedMap = this.sortMapOnFreq(map);
        Map HuffHenk = this.createTree(sortedMap);

        StringBuilder codedMessage = new StringBuilder();
        //N
        for (int i = 0; i < Message.length(); i++) {
            codedMessage.append(HuffHenk.get(Message.charAt(i)));
        }

        return codedMessage.toString();
    }

    /**
     * decodeert het bericht
     * @param codedMessage bericht dat moet worden omgegooit
     * @param huffMap codes om het weer tot karakters om te zetten
     * @return het bericht
     */
    public String Decodeer(String codedMessage, Map huffMap) {
        Map revHuffMap = new HashMap<>();
        Iterator<Map.Entry<String, String>> entries = huffMap.entrySet().iterator();
        //Log(N)
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            revHuffMap.put(entry.getValue(), entry.getKey());
        }

        StringBuilder Message = new StringBuilder();
        String current = "";
        int positie = 0;
        //log(log(N))
        while (positie != codedMessage.length()) {
            while (!revHuffMap.containsKey(current)) {
                current += codedMessage.substring(positie, positie + 1);
                positie++;
            }
            Message.append(revHuffMap.get(current));
            current = "";
        }

        return Message.toString();
    }
}
