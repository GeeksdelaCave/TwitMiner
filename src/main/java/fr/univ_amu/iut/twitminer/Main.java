package fr.univ_amu.iut.twitminer;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Salam aëlekoum world!");

        Twitter twitter = TwitterFactory.getSingleton();
        try {
            Status status = twitter.updateStatus("Salut ! Ça va ?");
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
