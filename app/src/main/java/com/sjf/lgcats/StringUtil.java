package com.sjf.lgcats;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Provides a compilation of string helper methods
 *
 * @author  Shomil Jain
 * @author  Quintin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
 */

public class StringUtil {

    public static ArrayList<ArrayList<String>> parseTSV (List<String> s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        for (String line : s) {
            answer.add((ArrayList<String>) Arrays.asList(line.split("\t")));
        }
        return answer;
    }

    public static ArrayList<ArrayList<String>> parseTSV (String s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        String[] lines = s.split("\n");
        for (String line : lines) {
            answer.add((ArrayList<String>) Arrays.asList(line.split("\t")));
        }
        return answer;
    }

    // Returns null if no content is found from the URL
    public static String getText(String url) {
        try {
            String content = downloadString(url);
            return content;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String downloadString(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine + "\n");
        in.close();
        return response.toString();
    }

    public static void parse () {
        new DownloadFiles().execute();
    }

    public static void downloadLinks() {
        System.out.println("Running download");
        String text = StringUtil.getText(Resources.getSystem().getString(R.string.LGCATSlinks));
        if (text != null) {
            System.out.println(text);
        } else {
            System.out.println("Null");
        }
    }

}
