package com.mehmetbalci.thoughtworks.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class DecimalFromRoman {

    //Mapping Roman Numbers to their decimal equivalent
    static final Map<String, Integer> romanMapping = new HashMap<>();

    static {
        romanMapping.put("I", 1);
        romanMapping.put("V", 5);
        romanMapping.put("X", 10);
        romanMapping.put("L", 50);
        romanMapping.put("C", 100);
        romanMapping.put("D", 500);
        romanMapping.put("M", 1000);
    }
    // Rules for the non-repeating roman numbers
    static final List<Character> nonRepeatingRomanNumbers = new ArrayList<>(Arrays.asList('D', 'L', 'V'));

    // Validate the roman number rules and then convert them to decimal equivalents
    public static int convertToDecimal{
        
    }

    //calculated complicated decimal numbers
    public static int calculateDecimal
    {
    }

    //process decimal numbers : rule I from X and V etc.
    public static int processDecimal
    {
    }

    // check the duplicate numbers for repetations and validate
    public static boolean validateNoRepeatations
    {
    }

    // If X, C, I and M can be repeated three times
    public static boolean validateRepeatations
    {
    }
}
