package com.mehmetbalci.thoughtworks.assignment;

import org.junit.Test;
public class TestClass {
    @Test
    //This test should return the roman numeral numbers
    public void romanShouldBeConvertedToDecimal() {
        System.out.println(DecimalFromRoman.calculateDecimal("XIX")); 
    }
    @Test
    //Test should return sentence defined types
    public void findSentenceType() {
        String sentenceToEvaluate = "tegj is L";
        // Returns Roman Numeral equivalnets
        System.out.println("\"" + sentenceToEvaluate + "\" is of type " + Input.classifyLines(sentenceToEvaluate)); 
    }
}
