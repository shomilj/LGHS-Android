package com.sjf.lgcats;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HarryWang on 5/2/17.
 */

public class StringUtil {



    public static ArrayList<ArrayList<String>> parseTSV (String s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        String[] lines = s.split("\n");
        for (String line : lines) {
            answer.add((ArrayList<String>) Arrays.asList(line.split("\t")));
        }
        return answer;
    }

}
