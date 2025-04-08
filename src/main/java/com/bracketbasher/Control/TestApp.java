package com.bracketbasher.Control;

import java.util.ArrayList;

import com.bracketbasher.Model.Node;
import com.bracketbasher.View.BracketDisplay;

import javafx.stage.Stage;
import javafx.application.Application;
/**
 * Hello world!
 */
public final class TestApp extends Application {

    @Override
    public void start(Stage primaryStage){
        Node[] bracket = {
            new Node("rhubarb"),
            new Node("rhubarb"),
            new Node("cliff"),
            new Node("moss"),
            new Node("cliff"),
            new Node("rhubarb"),
            new Node("lichen"),
            new Node("moss"),
            new Node("cliff"),
            new Node("chowder"),
        };
        BracketDisplay bd = new BracketDisplay(new Stage(),bracket);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
