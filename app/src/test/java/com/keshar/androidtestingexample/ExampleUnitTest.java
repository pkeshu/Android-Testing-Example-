package com.keshar.androidtestingexample;

import org.junit.Test;

import static com.keshar.androidtestingexample.FirstNameExtractor.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static final String FIRSTNAME = "Keshar";

    @Test
    public void extractFirstName_NullInput_ReturnEmptyString() {
        assertThat(extractFirstName(null), is(""));
    }

    @Test
    public void extractFirstName_EmptyString_ReturnEmptyString() {
        assertThat(extractFirstName(""), is(""));
    }

    @Test
    public void extractFirstName_Fullname_ReturnCorrectString() {
        assertThat(extractFirstName("Keshar paudel"), is(FIRSTNAME));
    }

    @Test
    public void extractFirstName_FullnameArounfwithWhiteSpace_ReturnCorrectString() {
        assertThat(extractFirstName("Keshar paudel   "), is(FIRSTNAME));
        assertThat(extractFirstName("   Keshar paudel"), is(FIRSTNAME));
        assertThat(extractFirstName("Keshar     paudel"), is(FIRSTNAME));
        assertThat(extractFirstName("    Keshar paudel   "), is(FIRSTNAME));
        assertThat(extractFirstName("  Keshar paudel     "), is(FIRSTNAME));
    }

    @Test
    public void extractFirstName_Firstname_ReturnCorrectString() {
        assertThat(extractFirstName("Keshar"), is(FIRSTNAME));
    }

    @Test
    public void extractFirstName_FirstnameAroundWhiteSpaces_ReturnCorrectString() {
        assertThat(extractFirstName("Keshar   "), is(FIRSTNAME));
        assertThat(extractFirstName("   Keshar"), is(FIRSTNAME));
        assertThat(extractFirstName("   Keshar   "), is(FIRSTNAME));
        assertThat(extractFirstName("  Keshar   "), is(FIRSTNAME));
    }
}