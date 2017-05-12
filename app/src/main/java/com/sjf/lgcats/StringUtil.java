package com.sjf.lgcats;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author  Shomil Jain
 * @author  Quentin Leary
 * @author  Cassandra Melax
 * @author  Harry Wang
 * @version 1.0
 * @since   1.0
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
