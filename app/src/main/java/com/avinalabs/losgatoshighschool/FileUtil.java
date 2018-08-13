//
// FileUtil.java
// LG CATS
//
// Developers: Shomil Jain, Cassandra Melax, Quintin Leary, and Harry Wang
// Copyright © 2017 Los Gatos High School. All rights reserved.
//
// FileUtil - holds utility methods for files
//

package com.avinalabs.losgatoshighschool;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FileUtil {

    public static final String FILE_F_LINKS = "Frontend.txt";
    public static final String FILE_B_LINKS = "Backend.txt";
    public static final String FILE_RESOURCES = "Resources.txt";

    // pre: none
    // post: returns the string from the file with name fileName.
    public static String readFromFile(String fileName, Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\r");
            }
            return String.valueOf(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    // pre: none
    // post: writes the file with content content to file with name fileName
    // context is necessary for sharedpreferences
    public static void writeToFile(String fileName, String content, Context context) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();

            System.out.println(readFromFile(fileName, context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Careful! Sometimes this is "\r\n" instead of just "\r"
    public static ArrayList<ArrayList<String>> parseTSV (String s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        String[] lines = s.split("\r");
        for (String line : lines) {
            answer.add(new ArrayList<String>(Arrays.asList(line.split("\t"))));
        }
        return answer;
    }

}
