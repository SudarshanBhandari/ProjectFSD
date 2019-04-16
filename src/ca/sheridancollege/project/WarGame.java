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
 * @author
 */
public class WarGame extends Application {

    Text txt = new Text();
    Text txt2 = new Text();
    Text txt3 = new Text();
    FlowPane textPanel = new FlowPane();
    FlowPane table = new FlowPane();
    Button flip = new Button("Filp");
    Button shuffle = new Button("Shuffle");
    Button displayCard = new Button("Display Card");
    GroupOfCards cg = new GroupOfCards();

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 900, 650);
        primaryStage.setTitle("War Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.setStyle("-fx-background-color: green");

        root.setCenter(table);
        table.setAlignment(Pos.CENTER);

        FlowPane playerPanel = new FlowPane();
        root.setLeft(playerPanel);
        playerPanel.setAlignment(Pos.TOP_CENTER);

        FlowPane computerPanel = new FlowPane();
        root.setRight(computerPanel);
        computerPanel.setAlignment(Pos.BOTTOM_CENTER);

        root.setTop(textPanel);
        textPanel.setAlignment(Pos.CENTER);

        alertBox("Welcome to War Game");

        startGame();

        flip.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ClearTable();
                flipCard();
                CheckWinner();
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
                txt2.setText("Deck Size:" + cg.computerPile.size());
                txt3.setText("Deck Size:" + cg.playerPile.size());
            }
        });
        Button reset = new Button("Reset Game");
        reset.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
            startGame();

            }
        });

        Button quit = new Button("Quit Game");
        quit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert quit = new Alert(AlertType.CONFIRMATION);
                quit.setTitle("Quit");
                quit.setHeaderText(null);
                quit.setContentText("Do you want to quit?");

                Optional<ButtonType> result = quit.showAndWait();

                if (result.get() == ButtonType.OK) {
                    primaryStage.close();
                }
            }
        });

        FlowPane buttonPanel = new FlowPane();
        buttonPanel.setAlignment(Pos.CENTER);
        root.setBottom(buttonPanel);
        buttonPanel.getChildren().addAll(flip, shuffle, displayCard, reset, quit);

        Image imgA = new Image("images/cardback.png");
        ImageView ivA = new ImageView(imgA);
        playerPanel.getChildren().addAll(ivA, txt3);
        ivA.setPreserveRatio(true);
        ivA.setFitWidth(100);

        Image imgB = new Image("images/cardback.png");
        ImageView ivB = new ImageView(imgB);
        computerPanel.getChildren().addAll(ivB, txt2);
        ivB.setPreserveRatio(true);
        ivB.setFitWidth(100);
        textPanel.getChildren().add(txt);

    }

    private void callTextMain(String message) {
        txt.setText(message);
        txt.setFill(Color.BLUE);
        txt.setFont(Font.font(20));

    }


    public void startGame() {
        cg.playerPile.clear();
        cg.computerPile.clear();
        cg.deck.clear();
        cg.tablePile.clear();
        ClearTable();
        callTextMain("Good Luck");
        txt2.setText("Computer");
        txt3.setText("Player");
        cg.initCards();
        cg.shuffle();
        cg.distributeCards();
        cg.shuffle();
        disableButtons(false);
    }

    public void flipCard() {
        Card c = cg.computerPile.remove(0);
        Card d = cg.playerPile.remove(0);
        cg.tablePile.add(d);
        DisplayCard(d, table);
        cg.tablePile.add(c);
        DisplayCard(c, table);
        txt2.setText("Computer");
        txt3.setText("Player");

        if (c.Value == d.Value) {
            callTextMain("Its a war ");
            callWar();

        } else if (c.Value > d.Value) {

            for (int i = cg.tablePile.size() - 1; i >= 0; i--) {
                Card a = cg.tablePile.remove(i);
                cg.computerPile.add(a);
            }
            callTextMain("Computer Win ");

        } else {

            for (int i = cg.tablePile.size() - 1; i >= 0; i--) {
                Card a = cg.tablePile.remove(0);
                cg.playerPile.add(a);
            }
            callTextMain("Player Win ");
        }
    }

    public void callWar() {

        Card c1 = cg.computerPile.remove(0);
        Card c2 = cg.computerPile.remove(0);
        Card c3 = cg.computerPile.remove(0);

        Card d1 = cg.playerPile.remove(0);
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
        DisplayCard(c3, table);

        if (c3.Value == d3.Value) {
            callTextMain("Its a war2 " + cg.tablePile.size());
            callWar();

        }
        if (c3.Value > d3.Value) {

            for (int i = cg.tablePile.size() - 1; i >= 0; i--) {
                Card a = cg.tablePile.remove(i);
                cg.computerPile.add(a);
            }
            callTextMain("Computer Wins");
        } else if (c3.Value < d3.Value) {

            for (int i = cg.tablePile.size() - 1; i >= 0; i--) {
                Card a = cg.tablePile.remove(i);
                cg.playerPile.add(a);
            }
            callTextMain("Player Wins");

        }

    }

    
    public void DisplayCard(Card c, FlowPane panel) {

        Image img1 = new Image("images/" + c.GetFileName());
        ImageView iv1 = new ImageView(img1);
        panel.getChildren().add(iv1);
        iv1.setPreserveRatio(true);
        iv1.setFitWidth(100);

    }

    public void ClearTable() {

        table.getChildren().clear();

    }

    
    public void CheckWinner() {
        if (cg.computerPile.isEmpty()) {
            disableButtons(true);
            alertBox("Player Wins");

        } else if (cg.playerPile.isEmpty()) {
            disableButtons(true);
            alertBox("Computer Wins");

        }

    }

    public void alertBox(String str) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(str);
        alert.showAndWait();
    }

    public void disableButtons(boolean isTrue) {
        if(isTrue){
        flip.setDisable(true);
        displayCard.setDisable(true);
        shuffle.setDisable(true);
        }
        else{
            flip.setDisable(false);
        displayCard.setDisable(false);
        shuffle.setDisable(false);
        }

    }
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   
}
