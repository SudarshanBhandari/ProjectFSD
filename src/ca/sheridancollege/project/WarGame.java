/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sarth
 */
public class WarGame extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
         BorderPane root= new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("War Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-background-color: #009900");
        
       
        FlowPane table= new FlowPane();
        root.setCenter(table);
        table.setAlignment(Pos.CENTER);
        
        FlowPane textPanel= new FlowPane();
        root.setTop(textPanel);
        textPanel.setAlignment(Pos.CENTER);
        
        Text txt=new Text();
        
        txt.setText("Welcome to Game");
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font(25));
        textPanel.getChildren().add(txt);
        
        Button flip = new Button("Filp");
        flip.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        Button shuffle = new Button("Shuffle");
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        Button displayCard = new Button("Display Card");
        displayCard.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        Button reset = new Button("Reset Game");
        reset.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        Button quit = new Button("Quit Game");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });      
        FlowPane buttonPanel = new FlowPane();
        buttonPanel.setAlignment(Pos.CENTER);
        root.setBottom(buttonPanel);
        buttonPanel.getChildren().addAll(flip,shuffle,displayCard,reset,quit);
    
       Image imgA= new Image("images/cardback.png");
       ImageView ivA= new ImageView(imgA);
       table.getChildren().add(ivA);
       ivA.setPreserveRatio(true);
       ivA.setFitWidth(100);
       
    }
    


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
