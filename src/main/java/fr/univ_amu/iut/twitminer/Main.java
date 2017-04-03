package fr.univ_amu.iut.twitminer;

import static fr.univ_amu.iut.twitminer.Converter.CSVtoTrans;
import static fr.univ_amu.iut.twitminer.Converter.decodeTrans;
import static fr.univ_amu.iut.twitminer.TwitGetter.getTwits;


public class Main {
    public static void main(String[] args) {
        //getTwits();
        CSVtoTrans("resultats.csv");

        decodeTrans("../../Downloads/apriori/dicdic.txt");
    }
}
