package com.bracketbasher.Control;

import java.util.ArrayList;
import java.util.Scanner;

import com.bracketbasher.Model.Node;
import com.bracketbasher.View.BracketDisplay;

import javafx.stage.Stage;

/**
 * Hello world!
 */
public final class App {
    private static Scanner input = new Scanner(System.in);
     // Declare but don't instantiate, we will instantiate this when we know the length of the input (It will be 2n)
    private static Node[] bracketList;
    public static void main(String[] args) {
        // This function should wait for a user to input a CSV string
        // Once entered, prompt them with pairs of choices and ask them if they prefer choice 1 or 2
        // Recursively do this until the entire list is filled
        // Once list is filled, create a new BracketDisplay with the list
        String[] csv = PromptForCSV(input);

    }
    // This function should prompt the user for a CSV, and return it converted into an array of strings. i.e:
    // rhubarb,lichen,cliffs -> {"rhubarb","lichen","cliffs"}
    public static String[] PromptForCSV(Scanner input){

    }

    // Construct a Node array twice the length of values where the second half (n/2 -> n) is just the csv
    public static Node[] ConvertStringsToHalfArray(String[] values){

    }

    // After filling half of bracketlist, fill out the rest by asking user questions
    public static void FillBracketList(){

    }
}
