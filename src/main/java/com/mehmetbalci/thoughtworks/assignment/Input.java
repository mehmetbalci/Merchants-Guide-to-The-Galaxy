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


}
