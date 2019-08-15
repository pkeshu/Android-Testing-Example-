package com.keshar.androidtestingexample;

public class FirstNameExtractor {
    public static String extractFirstName(String firstname) {

        if (firstname == null || firstname.isEmpty()) {
            return "";
        }
        String[] s = firstname.split(" ");
        for (String word : s) {
            if (!word.isEmpty()) {
                return word;
            }
        }
        return null;
    }
}
