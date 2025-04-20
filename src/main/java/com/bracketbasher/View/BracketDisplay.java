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
    private static final int INITIAL_X_SPACING = 200;
    private static final int Y_SPACING = 80;
    private static final int BOX_WIDTH = 80;
    private static final int BOX_HEIGHT = 30;
    private static final int START_Y = 50;
    private static final int MARGIN = 30;

    private void calculateMaxDepth(Node node, int currentDepth) {
        if (node == null) return;
        maxDepth = Math.max(maxDepth, currentDepth);
        calculateMaxDepth(node.getLeft(), currentDepth + 1);
        calculateMaxDepth(node.getRight(), currentDepth + 1);
    }

    private void drawNodeRecursively(Node node, double x, double y, double xOffset, int depth) { 
        if (node == null) return;

        // Calculate connection points
        double childY = y + Y_SPACING;
        double connectorY = y + BOX_HEIGHT + (Y_SPACING - BOX_HEIGHT) * 0.4; // 40% down the gap

        // Draw connections FIRST (so they appear behind nodes)
        if (node.getLeft() != null) {
            double leftChildX = x - xOffset;
            
            // Connection line parts
            Line vertical1 = new Line(x, y + BOX_HEIGHT, x, connectorY);
            Line horizontal = new Line(x, connectorY, leftChildX, connectorY);
            Line vertical2 = new Line(leftChildX, connectorY, leftChildX, childY);
            
            linesPane.getChildren().addAll(vertical1, horizontal, vertical2);
            drawNodeRecursively(node.getLeft(), leftChildX, childY, xOffset/2, depth+1);
        }

        if (node.getRight() != null) {
            double rightChildX = x + xOffset;
            
            Line vertical1 = new Line(x, y + BOX_HEIGHT, x, connectorY);
            Line horizontal = new Line(x, connectorY, rightChildX, connectorY);
            Line vertical2 = new Line(rightChildX, connectorY, rightChildX, childY);
            
            linesPane.getChildren().addAll(vertical1, horizontal, vertical2);
            drawNodeRecursively(node.getRight(), rightChildX, childY, xOffset/2, depth+1);
        }

        // Draw the node box LAST (so it appears on top)
        Rectangle box = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
        box.setFill(Color.LIGHTBLUE);
        box.setStroke(Color.BLACK);
        
        Text text = new Text(node.getData());
        text.setWrappingWidth(BOX_WIDTH - 10);
        text.setTextAlignment(TextAlignment.CENTER);
        
        StackPane nodeBox = new StackPane(box, text);
        nodeBox.setLayoutX(x - BOX_WIDTH/2);
        nodeBox.setLayoutY(y);
        nodesPane.getChildren().add(nodeBox);
    }

    public BracketDisplay(Stage stage, Node[] bracketList) {
        stage.setTitle("Tournament Bracket");

        Node root = buildBracketTree(bracketList, 0);
        calculateMaxDepth(root, 0);
        
        // Calculate dimensions
        int bracketWidth = (int)(Math.pow(2, maxDepth-1) * INITIAL_X_SPACING);
        int requiredWidth = Math.min(1200, bracketWidth + 2*MARGIN);
        int requiredHeight = (maxDepth + 2) * Y_SPACING + 2*MARGIN;
        
        // Draw the bracket
        double startX = requiredWidth / 2;
        drawNodeRecursively(root, startX, START_Y, INITIAL_X_SPACING, 0);

        Scene scene = new Scene(mainPane, requiredWidth, requiredHeight);
        stage.setScene(scene);
        stage.show();
    }

    private Node buildBracketTree(Node[] list, int index) {
        if (index >= list.length) return null;
        Node current = list[index]; 
        current.setLeft(buildBracketTree(list, 2*index + 1));
        current.setRight(buildBracketTree(list, 2*index + 2));
        return current;
    }
}