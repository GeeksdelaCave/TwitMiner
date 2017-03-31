package fr.univ_amu.iut.twitminer;

import twitter4j.*;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            System.out.println("Please tpye your searching expression: ");
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter fichier = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("resultats.csv"))));
            Query query = new Query(clavier.readLine());
            QueryResult result;
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    fichier.write("\"" + tweet.getCreatedAt().toString() + "\";\"@" + tweet.getUser().getScreenName() + "\";\"" + tweet.getText());
                    fichier.newLine();
                }
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
