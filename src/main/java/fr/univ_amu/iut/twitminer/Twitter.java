package fr.univ_amu.iut.twitminer;

import twitter4j.TwitterFactory;

public class Twitter {

    private static twitter4j.Twitter Twitter;

    private Twitter() { }

    public static twitter4j.Twitter getTwitterInstance() {
        if (Twitter == null)
            Twitter = new TwitterFactory().getInstance();
        return Twitter;
    }

}
