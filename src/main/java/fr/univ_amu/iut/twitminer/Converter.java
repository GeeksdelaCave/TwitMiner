package fr.univ_amu.iut.twitminer;

import java.io.*;
import java.util.*;

public class Converter {
    private static HashMap<Integer, String> dictionnaire = new HashMap<>();
    private static final String dicoPath = "dictionaire.dico";
    private static final String transPath = "twit.trans";

    public static void CSVtoTrans(String path) {
        try {
            BufferedReader fichier = new BufferedReader(new FileReader(path));
            BufferedWriter dico = new BufferedWriter(new FileWriter(dicoPath));
            BufferedWriter trans = new BufferedWriter(new FileWriter(transPath));

            String ligne;
            int j = 0;
            while((ligne = fichier.readLine()) != null) {
                System.out.println(++j + " " + ligne);
                ligne = ligne.substring(32,ligne.length());
                int cursor = 0;
                boolean begin = false;
                for(int i = 0; i < ligne.length(); ++i) {
                    if(ligne.charAt(i) == '\"') {
                        if(begin) {
                            String mot = ligne.substring(cursor+1,i).toLowerCase().replaceAll("[^a-z0-9]", "");
                            if(!(mot.contains("http")) && mot.hashCode() != 0) {
                                int code = mot.hashCode()<0?-mot.hashCode():mot.hashCode();
                                dictionnaire.put(code,mot);
                                trans.write(String.valueOf(code));
                                trans.flush();
                                trans.newLine();
                            }
                            begin = false;
                        } else {
                            cursor = i;
                            begin = true;
                        }
                    }
                }
            }
            for (Integer i : dictionnaire.keySet()) {
                dico.write(i.toString());
                dico.flush();
                dico.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void decodeTrans(String path) {
        BufferedReader fichier = null;
        try {
            fichier = new BufferedReader(new FileReader(path));
            Map<String,Integer> freqMot = new HashMap<>();
            String ligne;
            while((ligne = fichier.readLine()) != null) {
                int cursor = 0;
                String mot = null;
                int frequence;
                for(int i = 0; i < ligne.length(); ++i) {
                    if (ligne.charAt(i) == ' ') {
                        mot = dictionnaire.get(Integer.parseInt(ligne.substring(0,i)));
                    }
                    if(ligne.charAt(i) == '(') {
                        cursor = i;
                    }
                    if(ligne.charAt(i) == ')') {
                        freqMot.put(mot,Integer.parseInt(ligne.substring(cursor+1,i)));
                    }
                }
            }
            for (String mot : sortByValue(freqMot).keySet()) {
                System.out.println((mot!=null?mot:"∅") +" : "+ freqMot.get(mot));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* From : https://www.mkyong.com/java/how-to-sort-a-map-in-java/*/
    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
