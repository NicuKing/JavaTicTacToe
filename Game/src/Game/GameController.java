package Game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameController {

    @FXML
    private GridPane Field;

    @FXML
    private AnchorPane panel;

    @FXML
    private Button reset = new Button();

    @FXML
    private Label alert = new Label();

    @FXML
    private Label scorecount = new Label();

    int score;
    boolean turn = true;
    boolean streakX = false;
    boolean streakO = false;
    boolean streak = true;
    ArrayList<Button> Tiles = new ArrayList<>();
    HighscoreController hc;

    public void initialize(){

        //Das Spielfeld in die Mitte verscheiben
        Field.setTranslateX(52);
        Field.setTranslateY(55);

        //Label um alerts zu zeigen
        alert.setTranslateX(105);
        alert.setTranslateY(240);
        panel.getChildren().add(alert);

        //Button um spiel zu reseten
        reset.setText("Reset Game");
        reset.setTranslateX(105);
        reset.setTranslateY(20);
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alert.setText("");
                for(Button b : Tiles){
                    b.setText("");
                    b.setDisable(false);
                    b.setMouseTransparent(false);
                    b.setFocusTraversable(true);
                }
            }
        });
        panel.getChildren().add(reset);

        //highscore anzeige
        scorecount.setTranslateX(200);
        scorecount.setTranslateY(240);
        scorecount.setText(String.valueOf(score));
        panel.getChildren().add(scorecount);

        //Einfügen von Buttons für Spielfeld
        for(int i=1; i<=3;i++){
            for(int j=1;j<=3;j++){
                Button b = new Button();
                b.setMinHeight(50);
                b.setMinWidth(50);
                b.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(turn) {
                            b.setText("X");
                            turn = !turn;
                        } else {
                            b.setText("O");
                            turn = !turn;
                        }
                        b.setMouseTransparent(true);
                        b.setFocusTraversable(false);
                        //Check if game is over
                        if(checkGameOver()){
                            for(Button b : Tiles){
                                b.setDisable(true);
                            }
                            //check if streak still going
                            if(streak) {
                                scorecount.setText(String.valueOf(score));
                            }
                            if(!streak){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("HighscoreWindow.fxml"));
                                HighscoreController highscore = new HighscoreController(score);
                                loader.setController(highscore);
                                Stage stage = new Stage();
                                stage.setTitle("Highscore");
                                try {
                                    AnchorPane anchor = loader.load();
                                    stage.setScene(new Scene(anchor, 250, 350));
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                streak = true;
                                score = 1;
                                scorecount.setText(String.valueOf(score));
                            }
                        }
                    }
                });
                //Buttons dem feld hinzufügen
                Field.add(b, j ,i );
                Tiles.add(b);
            }
        }
    }

    //Schaut ob die Runde zu Ende ist, und wie der WinStreak eines Spieler aussieht
    public boolean checkGameOver(){
        boolean gameover = false;

        //Check if X wins
        for(int i=0; i<=2;i++){
            if(Tiles.get(i).getText() == "X" && Tiles.get(i+3).getText() == "X" && Tiles.get(i+6).getText() == "X") {
                alert.setText("X wins");
                gameover = true;
                if(streakO == true){
                    streak = false;
                    streakO = false;
                }
                streakX = true;
                score++;
            }
        }
        for(int i=0;i<=6;i+=3){
            if(Tiles.get(i).getText() == "X" && Tiles.get(i+1).getText() == "X" && Tiles.get(i+2).getText() == "X"){
                alert.setText("X wins");
                gameover = true;
                if(streakO == true){
                    streak = false;
                    streakO = false;
                }
                streakX = true;
                score++;
            }
        }
        if(Tiles.get(0).getText() == "X" && Tiles.get(4).getText() == "X" && Tiles.get(8).getText() == "X") {
            alert.setText("X wins");
            gameover = true;
            if(streakO == true){
                streak = false;
                streakO = false;
            }
            streakX = true;
            score++;
        }
        else if(Tiles.get(2).getText() == "X" && Tiles.get(4).getText() == "X" && Tiles.get(6).getText() == "X"){
            alert.setText("X wins");
            gameover = true;
            if(streakO == true){
                streak = false;
                streakO = false;
            }
            streakX = true;
            score++;
        }

        //Check if O wins
        for(int i=0; i<=2;i++){
            if(Tiles.get(i).getText() == "O" && Tiles.get(i+3).getText() == "O" && Tiles.get(i+6).getText() == "O") {
                alert.setText("O wins");
                gameover = true;
                if(streakX == true){
                    streak = false;
                    streakX = false;
                }
                streakO = true;
                score++;
            }
        }
        for(int i=0;i<=6;i+=3){
            if(Tiles.get(i).getText() == "O" && Tiles.get(i+1).getText() == "O" && Tiles.get(i+2).getText() == "O"){
                alert.setText("O wins");
                gameover = true;
                if(streakX == true){
                    streak = false;
                    streakX = false;
                }
                streakO = true;
                score++;
            }
        }
        if(Tiles.get(0).getText() == "O" && Tiles.get(4).getText() == "O" && Tiles.get(8).getText() == "O") {
            alert.setText("O wins");
            gameover = true;
            if(streakX == true){
                streak = false;
                streakX = false;
            }
            streakO = true;
            score++;
        }
        else if(Tiles.get(2).getText() == "O" && Tiles.get(4).getText() == "O" && Tiles.get(6).getText() == "O"){
            alert.setText("O wins");
            gameover = true;
            if(streakX == true){
                streak = false;
                streakX = false;
            }
            streakO = true;
            score++;
        }

        //Check if tie
        int counter = 0;
        for(Button b : Tiles){
            if(b.getText() != "") counter++;
            if(counter == 9) gameover = true;
        }

        /* This Part is only for testing the variables of the winning streak
        System.out.println("streak: "+String.valueOf(streak));
        System.out.println("streakX: "+String.valueOf(streakX));
        System.out.println("streakO: "+String.valueOf(streakO));
        System.out.println();
        */

        return gameover;
    }
}
