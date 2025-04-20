package com.bracketbasher.View;

import com.bracketbasher.Model.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class BracketDisplay {
    private Pane linesPane = new Pane();  // For connection lines
    private Pane nodesPane = new Pane();  // For node boxes
    private Pane mainPane = new Pane(linesPane, nodesPane); // Combined view
    private int maxDepth = 0;

    // Spacing constants
    private static final int INITIAL_X_SPACING = 200; // horizontal space between sibling nodes at bottom of tree
    private static final int Y_SPACING = 80; // vertical space between parent and child nodes
    private static final int BOX_WIDTH = 80; // width of each node's box
    private static final int BOX_HEIGHT = 30; // height of each node's box
    private static final int START_Y = 50; // y position of root node
    private static final int MARGIN = 30; // padding for the window

    private void calculateMaxDepth(Node node, int currentDepth) { // calculates the height of the tree, basically how many rounds there are
        if (node == null) return; // base case, stop if node is empty
        maxDepth = Math.max(maxDepth, currentDepth); // used to track max depth
        calculateMaxDepth(node.getLeft(), currentDepth + 1); // recurse left
        calculateMaxDepth(node.getRight(), currentDepth + 1); // recurse right
    }

    private void drawNodeRecursively(Node node, double x, double y, double xOffset, int depth) { 
        if (node == null) return; // base case, stop if node is empty

        // Calculate connection points
        double childY = y + Y_SPACING;  // y position of child nodes
        double connectorY = y + BOX_HEIGHT + (Y_SPACING - BOX_HEIGHT) * 0.4; // places horizontal connectors 40% down so they connect to the box nodes

        // Draw connections FIRST (so they appear behind nodes)
        if (node.getLeft() != null) {
            double leftChildX = x - xOffset; // x position of left child
            
            // Connection line parts
            Line vertical1 = new Line(x, y + BOX_HEIGHT, x, connectorY); // down line from parent
            Line horizontal = new Line(x, connectorY, leftChildX, connectorY); // right line to child
            Line vertical2 = new Line(leftChildX, connectorY, leftChildX, childY); // up line to child
            
            linesPane.getChildren().addAll(vertical1, horizontal, vertical2); // add lines to background
            drawNodeRecursively(node.getLeft(), leftChildX, childY, xOffset/2, depth+1); // recurse
        }

        if (node.getRight() != null) {
            double rightChildX = x + xOffset; // x position of right child
            
            Line vertical1 = new Line(x, y + BOX_HEIGHT, x, connectorY); // down line from parent
            Line horizontal = new Line(x, connectorY, rightChildX, connectorY); // left line to child
            Line vertical2 = new Line(rightChildX, connectorY, rightChildX, childY); // up line to child
            
            linesPane.getChildren().addAll(vertical1, horizontal, vertical2); // add lines to background
            drawNodeRecursively(node.getRight(), rightChildX, childY, xOffset/2, depth+1); // recurse
        }

        // Draw the node box LAST (so it appears on top)
        Rectangle box = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
        box.setFill(Color.LIGHTBLUE); // box fill color
        box.setStroke(Color.BLACK); // box outline color
        
        Text text = new Text(node.getData()); // get text from node data
        text.setWrappingWidth(BOX_WIDTH - 10); // text wrapping
        text.setTextAlignment(TextAlignment.CENTER); // centers text
        
        StackPane nodeBox = new StackPane(box, text);
        nodeBox.setLayoutX(x - BOX_WIDTH/2); // set x layout of box
        nodeBox.setLayoutY(y); // set y layout of box
        nodesPane.getChildren().add(nodeBox); // add box to pane
    }

    public BracketDisplay(Stage stage, Node[] bracketList) { // constructor, takes in bracket list
        stage.setTitle("Tournament Bracket"); // window title

        // build tree and calculate depth
        Node root = buildBracketTree(bracketList, 0);
        calculateMaxDepth(root, 0);
        
        // calculate dimensions
        int bracketWidth = (int)(Math.pow(2, maxDepth-1) * INITIAL_X_SPACING);
        int requiredWidth = Math.min(1200, bracketWidth + 2*MARGIN);
        int requiredHeight = (maxDepth + 2) * Y_SPACING + 2*MARGIN;
        
        // draw the bracket
        double startX = requiredWidth / 2;
        drawNodeRecursively(root, startX, START_Y, INITIAL_X_SPACING, 0);

        // display
        Scene scene = new Scene(mainPane, requiredWidth, requiredHeight);
        stage.setScene(scene);
        stage.show();
    }

    private Node buildBracketTree(Node[] list, int index) {
        if (index >= list.length) return null; // base case
        Node current = list[index]; 
        current.setLeft(buildBracketTree(list, 2*index + 1)); // left child
        current.setRight(buildBracketTree(list, 2*index + 2)); // right child
        return current;
    }
}