package com.sjf.lgcats;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by shomil on 5/20/17.
 */

public class FileUtil {

    public static final String FILE_LINKS = "Links.txt";
    public static final String FILE_HOTLINES = "Hotlines.txt";
    public static final String FILE_COUNTDOWN = "Countdown.txt";
    public static final String FILE_CALENDAR = "Calendar.txt";
    public static final String FILE_LOGINS = "StudentLogins.txt";

    // Returns the string from the file with name fileName.
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
