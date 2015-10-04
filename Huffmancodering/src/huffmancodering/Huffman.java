/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmancodering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import sun.misc.Queue;

/**
 *
 * @author Bas
 */
public class Huffman {

    public HashMap<HuffmanNode, Integer> huffmanMap = new HashMap<HuffmanNode, Integer>();

    private static class HuffmanNode {

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

    private static class HuffManComparator implements Comparator<HuffmanNode> {

        @Override
        public int compare(HuffmanNode node1, HuffmanNode node2) {
            return node1.frequency - node2.frequency;
        }
    }

    public HashMap countCharacters(String characters) {
        HashMap henkMap = new HashMap();
        int prevn = 0;
        String character;
        for (int i = 1; i < characters.length(); i++) {
            character = characters.substring(prevn, i);
            if (henkMap.containsKey(character)) {
                henkMap.replace(character, (int) henkMap.get(character) + 1);
            } else {
                henkMap.put(character, 1);
            }
            prevn = i;
        }

        return henkMap;
    }

    public Map sortHashMapOnFrequency(HashMap henkMap) {
        ArrayList<String> henklist = new ArrayList<String>();
        henkMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> henklist.add(entry.toString())); //Sorteren map op value
  
        Map sortedMap = new HashMap();
        for (String item : henklist) {
            String[] items = item.split("=");
            sortedMap.put(item.charAt(0), Integer.valueOf(items[1]));
        }
        return sortedMap;
    }

    private HuffmanNode getRoot(HashMap<Character,Integer> henkMap) {
        PriorityQueue<HuffmanNode> henkQ = new PriorityQueue<HuffmanNode>(henkMap.size(), new HuffManComparator());
        for (Entry<Character, Integer> entry : henkMap.entrySet()) {
            henkQ.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }
        while (henkQ.size() > 1) {
            HuffmanNode hn1 = (HuffmanNode) henkQ.remove();
            HuffmanNode hn2 = (HuffmanNode) henkQ.remove();
            HuffmanNode newNode = new HuffmanNode('*', hn1.frequency + hn2.frequency, hn1, hn2);
            henkQ.add(newNode);
        }
        return henkQ.remove();
    }

    public Map<Character, String> createTree(HashMap henkMap) {
        return generateCode(henkMap.keySet(), getRoot(henkMap));
    }

    private Map<Character, String> generateCode(Set keySet, HuffmanNode root) {
        Map<Character, String> henkMap = new HashMap<Character, String>();
        createNodeCode(root, henkMap, "");
        return henkMap;
    }

    private static void createNodeCode(HuffmanNode node, Map<Character, String> map, String s) {
        if (node.left == null && node.right == null) {
            map.put(node.ch, s);
            return;
        }
        createNodeCode(node.left, map, s + '0');
        createNodeCode(node.right, map, s + '1');
    }

}
