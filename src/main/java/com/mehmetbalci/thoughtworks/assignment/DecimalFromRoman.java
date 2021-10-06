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

    // process decimal numbers : rule I from X and V etc.
    public static int processDecimal(int currentDecimal, int previousDecimal, int decimalNumber) {
        // Implementation of rule I. We can get this subtracted from just V and X. X
        // could
        // be subtracted from C and L. C could be subtracted from M and D
        if (currentDecimal < previousDecimal && previousDecimal <= currentDecimal * 10) {
            return (decimalNumber - currentDecimal);
        } else {
            return (decimalNumber + currentDecimal);
        }
    }


    // Validate the roman number rules and then convert them to decimal equivalents
    public static int convertToDecimal{
        
    }

    // If X, C, I and M can be repeated three times
    public static boolean validateRepeatations
    {
    }

    //calculated complicated decimal numbers
    public static int calculateDecimal
    {
    }

    

    // check the duplicate numbers for repetations and validate
    public static boolean validateNoRepeatations
    {
    }

    
}
