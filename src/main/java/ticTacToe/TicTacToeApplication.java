package ticTacToe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class TicTacToeApplication extends Application {
    private Label turnText;
    
    @Override
    public void start(Stage window) {
        
        
        turnText = new Label("");
        turnText.setFont(Font.font(40));
        
        HBox top = new HBox();
        top.getChildren().add(turnText);
        top.setAlignment(Pos.CENTER);
        
        Button restart = new Button("Restart");
        HBox bottom = new HBox();
        bottom.getChildren().add(restart);
        bottom.setAlignment(Pos.CENTER);
        
        Parent tictacView = (new TictacView(turnText)).getView();
        
        BorderPane border = new BorderPane();

        border.setTop(top);
        border.setBottom(bottom);
        border.setCenter(create());
        
        restart.setOnAction(event ->  {
            border.setCenter(create());
        });
        
        
        Scene scene = new Scene(border);
        
        window.setScene(scene);
        window.show();
    }
    
    public Parent create() {
        return (new TictacView(turnText)).getView();
    }
    

    public static void main(String[] args) {
        launch(TicTacToeApplication.class);
        System.out.println("Hello world!");
    }

}
