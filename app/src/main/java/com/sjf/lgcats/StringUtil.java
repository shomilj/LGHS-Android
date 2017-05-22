package com.sjf.lgcats;

import android.content.res.Resources;
import android.widget.TextView;

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

    public static String getUrlContents(String theUrl) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\r");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    /*
    public static ArrayList<ArrayList<String>> parseTSV (String s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        String[] lines = s.split("\r");
        for (String line : lines) {
            answer.add((ArrayList<String>) Arrays.asList(line.split("\t")));
        }
        return answer;
    }*/
/*
    // Returns null if no content is found from the URL
    public static String getText(String url) {
        try {
            return downloadString(url);
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


    public static void parse (String url) {
        DownloadFiles task = (DownloadFiles) new DownloadFiles(url, new DownloadAsyncTaskInterface() {
            @Override
            public void saveDownload(String result) {
                System.out.println(result.substring(0, 30));
            }
        }).execute();
    }

    public static String downloadLinks(String url) {
        System.out.println("Running download");
        // String text = Resources.getSystem().getString(urlID);
        // apparently one of the above methods completely breaks the AsyncTask
        String text = StringUtil.getText(url);
        if (text != null) {
            return text;
        } else {
            return "error: string is null";
        }
    }
*/
}
