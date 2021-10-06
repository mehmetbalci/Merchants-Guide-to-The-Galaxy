package com.mehmetbalci.thoughtworks.assignment;

public class MainClass {
    //read sample text from file and process it
    public static void main(String[] args) {
        String fileLocation = null;
        if (args.length > 0) 
        {
            fileLocation = args[0];
        }
        try 
        {
            Input.ProcessInputFile(fileLocation);
        } 
        catch (Exception e) 
        {
            System.out.println("Text file not found in directory!!");
        }
    }
}