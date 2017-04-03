package fr.univ_amu.iut.twitminer;

import java.io.IOException;

public class AprioriInterface {
    public static void apriori(String in, String out, int seuil) {
        try {
            System.out.println("Apriori");
            Process pr = Runtime.getRuntime().exec("./apriori "+ in +" "+ seuil + " "+ out);
            int exitVal = pr.waitFor();
            System.out.println("Apriori (code  "+exitVal+")");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
