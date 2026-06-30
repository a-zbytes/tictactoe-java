
package ticTacToe;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


public class TictacView {
    private String turn;
    private Label turnText;
    private int turnCount;
    
    public TictacView(Label turnText) {
        this.turnText = turnText;
        this.turn = "X";
    }
    
    public Parent getView() {
        Button btn1 = createButton();
        Button btn2 = createButton();
        Button btn3 = createButton();
        Button btn4 = createButton();
        Button btn5 = createButton();
        Button btn6 = createButton();
        Button btn7 = createButton();
        Button btn8 = createButton();
        Button btn9 = createButton();
        
        GridPane grid = new GridPane();
        
        grid.setPadding(new Insets(10,10,10,10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        
        grid.add(btn1, 0, 0);
        grid.add(btn2, 1, 0);
        grid.add(btn3, 2, 0);
        grid.add(btn4, 0, 1);
        grid.add(btn5, 1, 1);
        grid.add(btn6, 2, 1);
        grid.add(btn7, 0, 2);
        grid.add(btn8, 1, 2);
        grid.add(btn9, 2, 2);
        
        
        
        turnText.setText("Turn: " + turn);
        
        BooleanProperty end = new SimpleBooleanProperty(false);
        
        EventHandler<ActionEvent> actions= event -> {
            Button click = (Button) event.getSource();
            
            if (!click.getText().isEmpty() || end.get()) {
                return;
            }
            
            click.setText(turn);
            turnCount++;
            
            if (check(grid)) {
                turnText.setText("The end! " + turn +" wins");
                end.set(true);
                return;
            }
            
            if(turnCount < 9) {
                nextTurn();
                turnText.setText("Turn: " + turn);
            } else {
                turnText.setText("The end! Tie");
                end.set(true);
            }
        };
        
        
        btn1.setOnAction(actions);
        btn2.setOnAction(actions);
        btn3.setOnAction(actions);
        btn4.setOnAction(actions);
        btn5.setOnAction(actions);
        btn6.setOnAction(actions);
        btn7.setOnAction(actions);
        btn8.setOnAction(actions);
        btn9.setOnAction(actions);
        
        return grid;
    }
    
    public Button createButton() {
        Button button = new Button("");
        
        button.setFont(Font.font("Monospaced", 40));
        button.setPrefSize(80, 80);
        
        return button;
    }
    
    public void nextTurn() {
        if (turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
    }
    
    public String getNode(GridPane grid, int column, int row) {
        for (Node node : grid.getChildren()) {
            int nodeColumn = GridPane.getColumnIndex(node);
            int nodeRow = GridPane.getRowIndex(node);
            
            if (column == nodeColumn && row == nodeRow) {
                return ((Button)node).getText();
            }
        }
        return "";
    }
    
    public boolean check(GridPane grid) {
        
        for (int column = 0; column <= 2; column++) {
            int count = 0;
            for (int row = 0; row <=2; row++) {
                String value = getNode(grid, column,row);
                
                if (turn.equals(value)) {
                    count++;
                        if (count >= 3) {
                            return true;
                        }
                    
                } else {
                    count = 0;
                }
            }
        }
        
        for (int column = 0; column <= 2; column++) {
            int count = 0;
            for (int row = 0; row <=2; row++) {
                String value = getNode(grid, row,column);
                
                if (turn.equals(value)) {
                    count++;
                        if (count >= 3) {
                            return true;
                        }
                } else {
                    count = 0;
                }
            }
        }
        
        int diag1Count = 0;
        for (int column = 0, row = 0; column <=2 ; column++, row++) {
            String value = getNode(grid, column,row);
            
                if (turn.equals(value)) {
                    diag1Count++;
                        if (diag1Count >= 3) {
                            return true;
                        }
                } else {
                    diag1Count = 0;
                }
        }
        
        int diag2Count = 0;
        for (int column = 2, row = 0; row <= 2; column--, row++) {
            String value = getNode(grid, column, row);
            
            if (turn.equals(value)) {
                    diag2Count++;
                        if (diag2Count >= 3) {
                            return true;
                        }
                } else {
                    diag2Count = 0;
                }
        }
        return false;
    }

    
}
