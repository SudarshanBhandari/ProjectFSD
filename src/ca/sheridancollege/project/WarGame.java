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
     Button flip = new Button("Filp");
      Button shuffle = new Button("Shuffle");
      Button displayCard = new Button("Display Card");
    GroupOfCards cg=new GroupOfCards();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane root= new BorderPane();
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("War Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-background-color: green");
        
       
       callTextMain("Good Luck");
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
        
        
        alertBox("Welcome to War Game");
       
        
        
        
        
       
        cg.initCards();
        cg.shuffle();
        cg.distributeCards();
        cg.shuffle();
        
        
        flip.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
              ClearTable();
              flipCard();
              CheckWinner() ;
             // flipComputerCard();
              //compareCard();
            }
        });
        
      
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                cg.shuffle();
            }
        });
        
        
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
       textPanel.getChildren().add(txt);
       
        
    }

    private void callTextMain(String message) {
        txt.setText(message);
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font(20));
        
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

public void flipCard() {
	Card c = cg.computerPile.remove(0);
        Card d = cg.playerPile.remove(0);
        cg.tablePile.add(d);
	DisplayCard(d, table);
        cg.tablePile.add(c);
        DisplayCard(c,table);
        
        
        
        if(c.Value==d.Value){
            callTextMain("Its a war "+cg.tablePile.size());
            callWar();
           
        }
        else if(c.Value>d.Value){
            
            
            for(int i=cg.tablePile.size()-1;i>=0; i--){
                Card a=cg.tablePile.remove(i);
                cg.computerPile.add(a);
            }
            callTextMain("Computer Win "+cg.tablePile.size());
           
        }
        else{
           
            for(int i=cg.tablePile.size()-1;i>=0; i--){
                Card a=cg.tablePile.remove(0);
                cg.playerPile.add(a);
            }
             callTextMain("Player Win "+cg.tablePile.size());
        }
	txt2.setText("Computer Deck Size:"+cg.computerPile.size());
        txt3.setText("Player Deck Size:"+cg.playerPile.size());
	}

public void callWar() {
    
    
     
        Card c1 = cg.computerPile.remove(0);
        Card c2 = cg.computerPile.remove(0);
        Card c3 = cg.computerPile.remove(0);
        
        Card d1= cg.playerPile.remove(0);
        Card d2 = cg.playerPile.remove(0);
        Card d3 = cg.playerPile.remove(0);
        
        ClearTable();
        cg.tablePile.add(d1);
         cg.tablePile.add(d2);
          cg.tablePile.add(d3);
	DisplayCard(d3, table);
        cg.tablePile.add(c1);
          cg.tablePile.add(c2);
            cg.tablePile.add(c3);
        DisplayCard(c3,table);

//        if(c3.Value==d3.Value){
//            callTextMain("Its a war2 "+cg.tablePile.size());
//            callWar();
//           
//        }
         if(c3.Value>d3.Value){
            
            for(int i=cg.tablePile.size()-1;i>=0; i--){
                Card a=cg.tablePile.remove(i);
                cg.computerPile.add(a);
            }
           callTextMain("COmputer Win2 "+cg.tablePile.size());
        }
         else if (c3.Value<d3.Value){
            
            
    for(int i=cg.tablePile.size()-1;i>=0; i--){
                Card a=cg.tablePile.remove(i);
                cg.playerPile.add(a);
            }
    callTextMain("Player Win2 "+cg.tablePile.size());
        
	}
         txt2.setText("Computer"+cg.computerPile.size());
        txt3.setText("Player"+cg.playerPile.size());
}
public void DisplayCard(Card c, FlowPane panel) {
     
		Image img1 = new Image("images/"+c.GetFileName());
		ImageView iv1 = new ImageView(img1);
		panel.getChildren().add(iv1);
		iv1.setPreserveRatio(true);
		iv1.setFitWidth(100);

	}
public void ClearTable(){
    
    table.getChildren().clear();

}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
   public void CheckWinner() 
    {
      if(cg.computerPile.isEmpty())
      {    disableButtons();
          alertBox("Player Wins");
         
      }
      else
          if(cg.playerPile.isEmpty())
          {disableButtons();
          alertBox("Computer Wins");
          
          }
      
   
   }
    public void alertBox(String str)
    {
    Alert alert= new Alert(AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText(str);
                    alert.showAndWait();
    }
    
    
    public void disableButtons(){
     flip.setDisable(true);
          displayCard.setDisable(true);
          shuffle.setDisable(true);
    
    }
    
    
    
}
