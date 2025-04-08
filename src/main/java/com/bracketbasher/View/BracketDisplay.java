package com.bracketbasher.View;
import java.util.ArrayList;
import com.bracketbasher.Model.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class BracketDisplay {
    private Pane pane;

    private static final int X_SPACING = 60; // constant for horizontal spacing of nodes
    private static final int Y_SPACING = 40;  // constant for vertical spacing of nodes

    private void drawNodeRecursively(Node node, double x, double y, double xOffset){ // draws nodes from bracket recursively
        if (node == null) return; // if node is empty exit recursive draw

        Label label = new Label(node.getData());
        label.setLayoutX(x); // set label x
        label.setLayoutY(y); // set label y
        label.setStyle("-fx-background-color: lightblue; -fx-border-color: black; -fx-padding: 5;"); // terrible background coloring
        pane.getChildren().add(label); // adds labels to pane

        if (node.getLeft() !=null) // if left child exists, draw left child
            drawNodeRecursively(node.getLeft(), x - xOffset, y + Y_SPACING, xOffset / 2);
        if (node.getRight() != null) // if right child exists, draw right child
            drawNodeRecursively(node.getRight(), x + xOffset, y + Y_SPACING, xOffset / 2);
        
    }
    public BracketDisplay(Stage stage, Node[] bracketList){
        stage.setTitle("Bracket");
        this.pane = new Pane();

        Node root = buildBracketTree(bracketList, 0);

        drawNodeRecursively(root, 400, 20, 150); 

        Scene scene = new Scene(pane, 800, 600);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private Node buildBracketTree(Node[] list, int index){
        if (index >= list.length) return null; // exit build bracket when index reaches end

        Node current = list[index]; 
        current.setLeft(buildBracketTree(list, 2 * index + 1));
        current.setRight(buildBracketTree(list, 2 * index + 2));
        return current;
    }
}