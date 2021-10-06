package com.mehmetbalci.thoughtworks.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Input {

    static Map<String, String> primitiveTokenMapping = new HashMap<>();
    static Map<String, Float> derivedTokenMapping = new HashMap<>();

    public static void ProcessInputFile(String filePath) throws IOException {
        BufferedReader bufferedReader;
        //If no file specified, pick the default location from the path 
        FileReader fileReader;
        //Locate sample input file in the directory
        if (filePath == null) {
            InputStream in = Input.class.getResourceAsStream("/file/sampleInput.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(in));
        } else {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
        }
        String line;
        //Read the file line by line and process it with buffer reader
        while ((line = bufferedReader.readLine()) != null) {
            // Classify lines into four types and process accordingly.
            // "ASSIGN PRIMITIVE" , "ASSIGN DERIVED", "QUESTION PRIMITIVE", "QUESTION DERIVED", "FAILURE"- type cannot be determined
            String answerText = "";
            switch (classifyLines(line)) {
                case "ASSIGN PRIMITIVE":
                    assignPrimitiveValues(line);//dont forget to write this                                //Function to assign primitive values
                    break;
                case "ASSIGN DERIVED":
                    demystifyDerivedValues(line);// dont forget to write this                               //Function to find and assign derived values.
                    break;
                case "QUESTION PRIMITIVE":
                    answerText = answerPrimitiveOnlyQuestion(line);// dont forget to write this             //Function to solve primitve value question
                    break;
                case "QUESTION DERIVED":
                    answerText = answerDerivedQuestion(line);// dont forget to write this                   //Function to solve derived value questions                } else {
                    break;
                default:
                    answerText = "FAILURE";
            }
            if (answerText.equals("FAILURE")) {
                answerText = "I have no idea what you are talking about";
            }
            if (!answerText.equals("SUCCESS") && !answerText.equals("")) {      //When not assigning values print output
                System.out.println(answerText);
            }
        }
        bufferedReader.close();
    }

    // 4 types - Primitive values, Derived Values, Questions:Primitive, Questions:Derived
    public static String classifyLines(String line) {
        String[] words = line.split(" ");
        // Type 1 - Primitive values
        if (words.length == 3 && words[1].equals("is")) {                       // Length = 3, 2nd word :'is', 3rd Letter is Roman
            return "ASSIGN PRIMITIVE";
            // Type2 - Derived values    
        } else if (words[words.length - 1].equalsIgnoreCase("Credits") && words[words.length - 3].equalsIgnoreCase("is")) { //Derived Values
            try {
                Integer.parseInt(words[words.length - 2]);                      //Check if the 2nd last value is an integer, else reject.
            } catch (NumberFormatException e) {
                return "FAILURE";
            }
            return "ASSIGN DERIVED";
            // Type 3 & 4 - Questions
        } else if (words[words.length - 1].equalsIgnoreCase("?") && words.length > 4) {
            String validateQn = "";
            //Checek for Questions:Primitive
            for (int i = 0; i < 3; i++) {
                validateQn = validateQn.concat(words[i] + " ");
            }
            if (validateQn.equalsIgnoreCase("how much is ")) {
                return "QUESTION PRIMITIVE";
                //Checek for Questions:Derived
            } else {
                validateQn = "";
                for (int i = 0; i < 4; i++) {
                    validateQn = validateQn.concat(words[i] + " ");
                }
                if (validateQn.equalsIgnoreCase("how many Credits is ")) {
                    return "QUESTION DERIVED";
                }
            }
        }
        return "FAILURE";
    }

    // Assign primitive values to its correspondences
    public static String assignPrimitiveValues(String line){
        //split words with spaces
        String[] words = line.split(" ");
        //If the string has valid roman numeral then put it to the primitive map
        if (DecimalFromRoman.romanMapping.containsKey(words[2])) {
            primitiveTokenMapping.put(words[0], words[2]);
            return "SUCCESS";
        }
        //none of ifs applies return fail
        return "FAIL";
    }

    // Calculate derived values
    public static String demystifyDerivedValues(String line) {
        String[] words = line.split(" ");
        // If string has valid primitiveToken
        for (int i = 0; i < words.length - 4; i++) {
            // If the word is in the string not defined in the language.
            if (!primitiveTokenMapping.containsKey(words[i])) {
                return "FAIL";
            }
        }
        // ramon string built
        StringBuilder romanString = new StringBuilder();
        //append characters to the romanstring
        for (int i = 0; i < words.length - 4; i++) 
        {
            romanString.append(primitiveTokenMapping.get(words[i]));
        }
        int decimal = DecimalFromRoman.convertToDecimal(romanString.toString());
        if (decimal == 0) 
        {
            return "FAIL";
        } 
        //success
        else 
        {
            float derivedValue = Float.parseFloat(words[words.length - 2]) / decimal;
            derivedTokenMapping.put(words[words.length - 4], derivedValue);
            return "SUCCESS";
        }
    }

    //Calculate questions with primitive values
    public static String answerPrimitiveOnlyQuestion(String line) {
        String[] words = line.split(" ");
        // If string has valid primitiveToken
        for (int i = 3; i < words.length - 1; i++) {
            // If word in the string not defined in the language.
            if (!primitiveTokenMapping.containsKey(words[i])) {
                return "FAIL";
            }
        }
        StringBuilder romanString = new StringBuilder();
        for (int i = 3; i < words.length - 1; i++) {
            romanString.append(primitiveTokenMapping.get(words[i]));
        }
        // Decimal equivalent for the Roman Number is the answer.
        int decimal = DecimalFromRoman.convertToDecimal(romanString.toString());
        if (decimal == 0) {
            return "FAIL";
        } else {
            String output = "";
            for (int i = 3; i < words.length - 1; i++) {
                output = output.concat(words[i] + " ");
            }
            output = output.concat("is " + decimal);
            return (output);
        }
    }

    // Calculate questions with primitive values like glob, prok etc..
    public static String answerDerivedQuestion(String line) {
        String[] words = line.split(" ");
        // If string has valid primitive token at 5th to 3rd last
        for (int i = 4; i < words.length - 2; i++) {
            // If word in the string not defined in the language. FAIL
            if (!primitiveTokenMapping.containsKey(words[i])) {
                return "FAIL";
            }
        }
        // 2nd needs to be derived type
        if (!derivedTokenMapping.containsKey(words[words.length - 2])) {
            return "FAIL";
        }
        // Calculate decimal equivalent of primitive values and append it to romanstring
        StringBuilder romanString = new StringBuilder();
        for (int i = 4; i < words.length - 2; i++) {
            romanString.append(primitiveTokenMapping.get(words[i]));
        }
        int decimal = DecimalFromRoman.convertToDecimal(romanString.toString());
        if (decimal == 0) {
            return "FAIL";
        } else {
            String output = "";
            for (int i = 4; i < words.length - 1; i++) {
                output = output.concat(words[i] + " ");
            }
            //Decimal of Roman String times Derived variable equals solution
            float derivedValue = decimal * derivedTokenMapping.get(words[words.length - 2]);
            output = output.concat("is " + Math.round(derivedValue) + " Credits");
            return (output);
        }
    }
}
