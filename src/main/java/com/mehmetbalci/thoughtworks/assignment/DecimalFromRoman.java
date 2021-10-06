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

    // If X, C, I and M can be repeated three times
    public static boolean validateRepeatations(String roman) {
        //roman numbers as a char list
        char[] result = roman.toCharArray();
        //iterators for array
        int i, j;
        // Check if the number of length is greater than 3 or not
        if (result.length > 3) {
            for (i = 0; i < result.length - 3; i++) {
                // If sucessive 3 are equal. i, i+1 and i+2 corresponds
                if ((result[i] == result[i + 1]) && (result[i + 1] == result[i + 2])) {
                    // If 4 in sucession, return false cause failed (i+3)
                    if (result[i] == result[i + 3]) {
                        return false;
                    }
                    if (result.length > i + 5) {
                        // If 1, 2, 3 and 5th are equal, check if 4th one less than 5th or not
                        if (result[i + 4] == result[i]) {
                            // If 3th greater than 4th, fail
                            if (romanMapping.get(Character.toString(result[i + 3])) > romanMapping
                                    .get(Character.toString(result[i + 4]))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    // check the duplicate numbers for repetations and validate
    public static boolean validateNoRepeatations(String roman) {
        char[] result = roman.toCharArray();
        int i, j;
        //control i and jth values in array
        for (i = 0; i < result.length; i++) {
            for (j = i + 1; j < result.length; j++) {
                if (result[i] == result[j]) {
                    // Control if the duplicated value is against rules we got or not
                    if (nonRepeatingRomanNumbers.contains(result[i])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Validate the roman number rules and then convert them to decimal equivalents
    public static int convertToDecimal(String roman) {
        // to avoid unforseen complications in CASE statements
        roman = roman.toUpperCase(); 
        // Validate repeatitions of D, L, V
        if (validateNoRepeatations(roman)) { 
            // Validate I, X, C, M rules repeat rules
            if (validateRepeatations(roman)) { 
                return (calculateDecimal(roman));//this returns decimal value. chech here while writing this function
            }
        }
        return 0; // Roman system does not have 0. so we need  to handle here
    }

    
    //calculated complicated decimal numbers
    public static int calculateDecimal(String romanNumber) {
        int decimal = 0; //this will store final value of decimal equivalent
        int prevNumber = 0; //this stores the previous parsed number 
        //we use this to decide wether to subtract or add the current number
        // Parse roman string in reverse order to better convertion.
        for (int x = romanNumber.length() - 1; x >= 0; x--) {
            char toDecimalValue = romanNumber.charAt(x);
            // get decimal value from the romanmMapping with trim functions
            int currentDecimal = romanMapping.get(Character.toString(toDecimalValue)); 
            //decimal value swith case to do corresponding process
            switch (toDecimalValue) {
                case 'I':
                    decimal = processDecimal(currentDecimal, prevNumber, decimal);
                    break;

                case 'V':
                    decimal += currentDecimal; // "V" never be subtracted.
                    break;

                case 'X':
                    decimal = processDecimal(currentDecimal, prevNumber, decimal);
                    break;

                case 'L':
                    decimal += currentDecimal; // "L" never be subtracted.
                    break;

                case 'C':
                    decimal = processDecimal(currentDecimal, prevNumber, decimal);
                    break;

                case 'D':
                    decimal += currentDecimal; //"D" never be subtracted.
                    break;

                case 'M':
                    decimal = processDecimal(currentDecimal, prevNumber, decimal);
                    break;
            }
            // Save the current processed roman's decimal equivalent.
            prevNumber = currentDecimal;
        }
        //return the final decimal value
        return decimal;
    }

    

    

    
}
