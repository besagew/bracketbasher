package com.bracketbasher.Control;

import java.util.Scanner;



import com.bracketbasher.Model.Node;
import com.bracketbasher.View.BracketDisplay;

import javafx.application.Application;
import javafx.stage.Stage;

public final class App extends Application {
    private static Scanner input = new Scanner(System.in);

    private static Node prompt(Scanner input, Node choice1Node, Node choice2Node){
        String response = "";
        Node returnChoice = null;

        // If no other node is provided, we have a winner by default
        if (choice2Node == null) {
            return choice1Node;
        } else if (choice1Node == null) {
            return choice2Node;
        }
        // Repeatedly prompt for input if response isn't a or b
        while (!(response.equals("a") || response.equals("b"))){
            System.out.println("Which wins?");
            System.out.println(choice1Node.getData() + " / " + choice2Node.getData());
            System.out.println("A - " + choice1Node.getData() + " B - " + choice2Node.getData());
            response = input.nextLine().toLowerCase();
            if (response.equals("a")) {
                returnChoice = choice1Node;
            } else if (response.equals("b")){
                returnChoice = choice2Node;
            }
        }
        return returnChoice;
    }
    @Override
    public void start(Stage primaryStage) {
        // This function should wait for a user to input a CSV string
        // Once entered, prompt them with pairs of choices and ask them if they prefer choice 1 or 2
        // Recursively do this until the entire list is filled
        // Once list is filled, create a new BracketDisplay with the list
        // String[] csv = PromptForCSV(input);
        // Node[] bracketDisplay = ConvertStringsToHalfArray(csv);
        Node[] bracketList =  new Node[]{
            null,
            null,
            null,
            null,
            null,
            null,
            new Node("rhubarb"),
            new Node("lichen"),
            new Node("moss"),
            new Node("cliff"),
            new Node("radiator"),
            new Node("hexagon"),
        };
        Node[] bl = FillBracketList(bracketList);
        //BracketDisplay bd = new BracketDisplay(primaryStage, bl);
    }

    // This function should prompt the user for a CSV, and return it converted into an array of strings. i.e:
    // rhubarb,lichen -> {"rhubarb","lichen"}
    // this function should NOT allow for an odd number of values to be inputted. It causes a huge headache so just don't let people do it
    public static String[] PromptForCSV(Scanner input){

        String[] values = null;

        while(true) {
            System.out.println("Enter an even length list of items separated by a comma");
            String line = input.nextLine().trim();
            
            // splitting and trimming each one to add to values
            values = line.split(",");
            for (int i = 0 ; i < values.length; i++) {
                values[i] = values[i].trim();
            }

            // checking if odd or empty for edges
            if (values.length % 2 != 0) {
                System.out.println("Invalid: Odd number of values entered");
            }
            else if (values.length == 0) {
                System.out.println("Empty input");
            } 
            else {
                break;
            }


        }

        return values;
        //String[] csvList = new String[]  The length of this will be equal to the # of commas in the input plus one :)
    }

    // Construct a Node array twice the length of values where the second half (n/2 -> n) is the values
    // {"rhubarb","lichen","cliffs"} --> {[],[],[],["rhubarb"],["lichen"],["cliffs"]}
    public static Node[] Nodeify(String[] values){
        // This function should convert all the strings into nodes, then make that subarry of nodes the last
        // half of the returned array

        // cretaing array with size double that of the input to represent a binary tree
        Node[] returnList = new Node[values.length * 2];

        // fill the second half with the user vals
        for (int i = 0; i < values.length;i++) {
            returnList[values.length + i] = new Node(values[i]);
        }
        return returnList;
    }

    // After filling half of bracketlist, fill out the rest by asking user questions
    // Calvin can probably do this. Hopefully
    public static Node[] FillBracketList(Node[] bracketList){
        for (int i = (bracketList.length-1); i > 0; i = i - 2){
            Node choice1 = bracketList[i];
            Node choice2 = bracketList[i-1];
            Node winner = prompt(input, choice1, choice2);
            System.out.println("Inserting " + winner + " at " + ((i-1)/2));
            bracketList[(i-1)/2] = new Node(winner.getData());
        }
        for (int nodeIndex = 1; nodeIndex < bracketList.length; nodeIndex++){
            Node node = bracketList[nodeIndex];
            if (node != null) {
                System.out.println("[" + nodeIndex + "]: " + node.getData());
            } else {
                System.out.println("[" + nodeIndex + "]: null");
            }
        }
        return bracketList;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
