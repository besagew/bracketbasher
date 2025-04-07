package com.bracketbasher.View;
import java.util.ArrayList;
import com.bracketbasher.Model.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BracketDisplay {
    private GridPane gridPane;

    private void drawNodeRecursively(int x1, int y1, int x, int y, Node node){
        
    }
    public BracketDisplay(Stage stage, Node[] bracketList){
        stage.setTitle("Bracket");
        GridPane gp = new GridPane();
        this.gridPane = gp;

        Scene scene = new Scene(gp,300,200);
        stage.setScene(scene);
        stage.showAndWait();
    }
}