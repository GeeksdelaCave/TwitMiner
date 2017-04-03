package fr.univ_amu.iut.twitminer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GeoLocalisation {
    private double latitude;
    private double longitude;

    private String countryCode;

    public GeoLocalisation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        locate();
    }

    private void locate() {
        try {
            URL website = new URL("http://ws.geonames.org/countryCodeJSON?lat="
                    + latitude + "&lng="
                    + longitude + "&username=demo");
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));

            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);

            in.close();

            System.out.println(response);

            Gson g = new Gson();
            Pays pays = g.fromJson(response.toString(), Pays.class);
            countryCode = pays.getCountryCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return countryCode;
    }

    public static void main(String[] args) {
        GeoLocalisation geo = new GeoLocalisation(45.03, 17.2);
        System.out.println(geo);
    }
}
