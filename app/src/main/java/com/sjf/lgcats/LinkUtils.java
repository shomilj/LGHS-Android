package com.sjf.lgcats;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shomil on 5/19/17.
 */

public class LinkUtils {

    public static final String FILE_LINKS = "Links.txt";
    public static final String FILE_HOTLINES = "Hotlines.txt";
    public static final String FILE_COUNTDOWN = "Countdown.txt";
    public static final String FILE_CALENDAR = "Calendar.txt";
    public static final String FILE_LOGINS = "StudentLogins.txt";

    public static final String LINK_LINKS = "https://docs.google.com/spreadsheets/d/1SLgWueqyOlvqEvW5ZVi4-nrUzpJAYlikSDlnssoXKGc/export?format=tsv";
    public static final String LINK_HOTLINES = "https://docs.google.com/spreadsheets/d/1cGpoo0sXmSu4vz4d3D6TtmTaaKdkbejvG9LWWrpBKxo/export?format=tsv";
    public static final String LINK_COUNTDOWN = "https://docs.google.com/spreadsheets/d/1oU_gAjgVvUzYs3IJ0-iByAPM4WRodCPUZnCt3XNozK0/export?format=tsv";
    public static final String LINK_CALENDAR = "https://docs.google.com/spreadsheets/d/1DnkOQhamCZkuyPz72aWr8RmgrIQEPNu51pzreLVWlCI/export?format=tsv";
    public static final String LINK_LOGINS = "https://docs.google.com/spreadsheets/d/1WgsrbGMCIK1ardLnqorygSUs0dKksJFNVpiR5TzONV0/export?format=tsv";

    // Returns the link associated with key.
    public static String getLink(String key, Context context) {
        String file = readFromFile(FILE_LINKS, context);

        HashMap<String, String> links = new HashMap<>();

        String[] list = file.split("\r");
        for (String s : list) {
            String[] cols = s.split("\t");
            if (cols.length > 2) {
                links.put(cols[1], cols[2]);
            }
        }

        return links.get(key);
    }

    // Returns the string from the file with name fileName.
    private static String readFromFile(String fileName, Context context) {
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

    // HOW TO WRITE TO A FILE IN INTERNAL STORAGE
    public static void writeToFile(String fileName, String content, Context context) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
