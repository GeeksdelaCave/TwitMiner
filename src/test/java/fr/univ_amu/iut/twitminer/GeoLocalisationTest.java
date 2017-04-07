package fr.univ_amu.iut.twitminer;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeoLocalisationTest {

    @Test
    public void testGeoToStringFrance() {
        GeoLocalisation Geo = new GeoLocalisation(48.5124, 2.2107);
        assert ("FR".equals(Geo.toString()));
    }



    @Test
    public void testGeoToStringRussie() {
        GeoLocalisation Geo = new GeoLocalisation(59.5602, 30.1822);
        assert ("RU".equals(Geo.toString()));
    }



    @Test
    public void testGeoToStringJapon() {
        GeoLocalisation Geo = new GeoLocalisation(35.4015, 139.4619);
        assert ("JP".equals(Geo.toString()));
    }

}
