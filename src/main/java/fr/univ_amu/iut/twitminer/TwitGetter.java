package fr.univ_amu.iut.twitminer;

import twitter4j.*;

import java.io.*;
import java.util.List;

public class TwitGetter {
    public static void getTwits() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter fichier = new BufferedWriter(new FileWriter("resultats.csv",true));

            Query query = new Query(clavier.readLine());
            query.setCount(10);
            QueryResult result;

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    fichier.write("\"" + tweet.getCreatedAt().toString()
                            + "\";\"@" + tweet.getUser().getScreenName()
                            + "\";\"" +  tweet.getText().replace(" ","\";\"").replace("\n"," "));
                    fichier.write("\";");
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
