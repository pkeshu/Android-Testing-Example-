package com.keshar.androidtestingexample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class FileReaderUtil {
    public static String readFile(ClassLoader classLoader, String fileName) {
        String result = "";

        try {
            URL resourse = classLoader.getResource(fileName);
            File f = new File(resourse.toURI());
            Scanner in = new Scanner(new FileReader(f));
            StringBuilder stringBuilder = new StringBuilder();
            while (in.hasNextLine()) {
                stringBuilder.append(in.nextLine());
            }
            result = stringBuilder.toString();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }catch (NullPointerException n){
            n.printStackTrace();
        }
        return result;
    }
}
