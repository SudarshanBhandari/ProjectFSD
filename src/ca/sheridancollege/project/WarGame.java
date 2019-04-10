/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.sheridancollege.project;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    
    
    Text txt=new Text();
    Text txt2=new Text();
    Text txt3=new Text();
    FlowPane textPanel= new FlowPane();
    FlowPane table= new FlowPane();
    GroupOfCards cg=new GroupOfCards();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root= new BorderPane();
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("War Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-background-color: green");
        
       
       
        root.setCenter(table);
        table.setAlignment(Pos.CENTER);
        
        FlowPane playerPanel=new FlowPane();
        root.setLeft(playerPanel);
        playerPanel.setAlignment(Pos.TOP_CENTER);
        
        FlowPane computerPanel=new FlowPane();
        root.setRight(computerPanel);
        computerPanel.setAlignment(Pos.BOTTOM_CENTER);
        
        
        root.setTop(textPanel);
        textPanel.setAlignment(Pos.CENTER);
        
        txt2.setText("Computer");
        txt3.setText("Player");
        
        Alert welcome= new Alert(AlertType.INFORMATION);
        welcome.setTitle("Welcome");
        welcome.setHeaderText(null);
        welcome.setContentText("Welcome to War Game");
        welcome.showAndWait();
        
        callTextMain("Good Luck");
        
        Button flip = new Button("Filp");
        cg.initCards();
        cg.shuffle();
        cg.distributeCards();
        cg.shuffle();
        
        
        flip.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
              flipPlayerCard();
              flipComputerCard();
            }
        });
        
        Button shuffle = new Button("Shuffle");
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                cg.shuffle();
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
                //first();
                
            }
        });
        
        Button quit = new Button("Quit Game");
        quit.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                Alert quit= new Alert(AlertType.CONFIRMATION);
                    quit.setTitle("Quit");
                    quit.setHeaderText(null);
                    quit.setContentText("Do you want to quit?");
                    
                Optional<ButtonType> result=quit.showAndWait();
                
                if(result.get()==ButtonType.OK){
                primaryStage.close();
            }}
        });      
        
       FlowPane buttonPanel = new FlowPane();
       buttonPanel.setAlignment(Pos.CENTER);
       root.setBottom(buttonPanel);
       buttonPanel.getChildren().addAll(flip,shuffle,displayCard,reset,quit);
    
       Image imgA= new Image("images/cardback.png");
       ImageView ivA= new ImageView(imgA);
       playerPanel.getChildren().addAll(ivA,txt3);
       ivA.setPreserveRatio(true);
       ivA.setFitWidth(100);
       
       Image imgB= new Image("images/cardback.png");
       ImageView ivB= new ImageView(imgB);
       computerPanel.getChildren().addAll(ivB,txt2);
       ivB.setPreserveRatio(true);
       ivB.setFitWidth(100);
       
       
        
    }

    private void callTextMain(String message) {
        txt.setText(message);
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font(20));
        textPanel.getChildren().add(txt);
    }
    
//public void first(){
//     
//       
//       cg.initCards();
//       
//       for(Card a: cg.deck){
//           System.out.println(a.getValue()+"of"+a.getSuit());
//       }
//       
//       cg.distributeCards();
//    }

public void flipComputerCard() {
	Card c = cg.computerPile.remove(0);
	cg.tablePile.add(c);
        DisplayCard(c,table);
	}

public void flipPlayerCard() {
		Card c = cg.playerPile.remove(0);
		cg.playerPile.add(c);
		DisplayCard(c, table);

	}
public void DisplayCard(Card c, FlowPane panel) {
     
		Image img1 = new Image("images/"+c.GetFileName());
		ImageView iv1 = new ImageView(img1);
		panel.getChildren().add(iv1);
		iv1.setPreserveRatio(true);
		iv1.setFitWidth(100);

	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
