package com.keshar.androidtestingexample;

import org.junit.Before;
import org.junit.Test;

import static com.keshar.androidtestingexample.FileReaderUtil.*;
import static com.keshar.androidtestingexample.JsonParser.*;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParseLocationFromJsonTest {
    private static String CORRECT_CITY_NAME = "Dallas, TX, USA";
    private static double CORRECT_LAT = 32.7766642;
    private static double CORRECT_LON = -96.79698789999999;
    private static double NULL_COORDINATE = 0.0;

    //json input

    private static String correctInput, emptyResult, noCity, noCityAndLocation, noGeometry, noLatitude, noLocation, noLongitude, noResult;

    @Before
    public void readJsonFilesToString() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        correctInput = readFile(classLoader, "correctInput.json");
        emptyResult = readFile(classLoader, "emptyResults.json");
        noCity = readFile(classLoader, "noCity.json");
        noCityAndLocation = readFile(classLoader, "noCityAndLocation.json");
        noGeometry = readFile(classLoader, "noGeometry.json");
        noLatitude = readFile(classLoader, "noLatitude.json");
        noLocation = readFile(classLoader, "noLocation.json");
        noLongitude = readFile(classLoader, "noLongitude.json");
        noResult = readFile(classLoader, "noResults.json");
    }

    @Test
    public void parseLocationfromJson_NullInput_EmptyLocation() {
        Location location = parseLocationFromJson(null);
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationfromJson_EmptyInput_EmptyLocation() {
        Location location = parseLocationFromJson("");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationfromJson_EmptyJson_EmptyLocation() {
        Location location = parseLocationFromJson("{}");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationfromJson_NotAJsonInput_EmptyLocation() {
        Location location = parseLocationFromJson("Some Word");
        assertThatLocationIsEmpty(location);
    }

    @Test
    public void parseLocationfromJson_CorrectInput_ReturnLocation() {
        Location location = parseLocationFromJson(correctInput);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(CORRECT_LAT));
        assertThat(location.getLongitude(), is(CORRECT_LON));
    }

    @Test
    public void parseLocationfromJson_EmptyResult_NullEverything() {
        Location location = parseLocationFromJson(emptyResult);
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationfromJson_NoCity_NullCity() {
        Location location = parseLocationFromJson(noCity);
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(CORRECT_LAT));
        assertThat(location.getLongitude(), is(CORRECT_LON));
    }

    @Test
    public void parseLocationfromJson_NoCityAndLocation_NullCityAndZeroLatLng() {
        Location location = parseLocationFromJson(noCityAndLocation);
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationfromJson_NoGeometry_NCityAndZeroLatLng() {
        Location location = parseLocationFromJson(noGeometry);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationfromJson_NoLatitude_ZeroLat() {
        Location location = parseLocationFromJson(noLatitude);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(CORRECT_LON));
    }

    @Test
    public void parseLocationfromJson_NoLongitude_ZeroLon() {
        Location location = parseLocationFromJson(noLongitude);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(CORRECT_LAT));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationfromJson_NoLocation_ZeroLatLng() {
        Location location = parseLocationFromJson(noLocation);
        assertThat(location.getCityName(), is(CORRECT_CITY_NAME));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    @Test
    public void parseLocationfromJson_NoResult_NullCityZeroLatLng() {
        Location location = parseLocationFromJson(noResult);
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }

    private void assertThatLocationIsEmpty(Location location) {
        assertThat(location.getCityName(), is(nullValue()));
        assertThat(location.getLatitude(), is(NULL_COORDINATE));
        assertThat(location.getLongitude(), is(NULL_COORDINATE));
    }


}