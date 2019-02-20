package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    ArrayList<Button> buttons = new ArrayList<Button>();
    Label alert = new Label();
    Label score = new Label();
    int countscore = 0;
    boolean turn = true;
    boolean streakX = false;
    boolean streakO = false;
    boolean streak = true;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group game = new Group();
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(new Scene(game, 300, 275));
        //Label um alerts zu zeigen
        alert.setTranslateX(105);
        alert.setTranslateY(220);
        game.getChildren().add(alert);

        //Button um spiel zu reseten
        Button reset = new Button("reset");
        reset.setTranslateX(105);
        reset.setTranslateY(20);
        //Reset Button Event
        reset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                alert.setText("");
                for(Button b : buttons){
                    b.setText("");
                    b.setDisable(false);
                    b.setMouseTransparent(false);
                    b.setFocusTraversable(true);
                }
            }
        });
        game.getChildren().add(reset);

        //highscore anzeige
        score.setTranslateX(145);
        score.setTranslateY(220);
        score.setText(String.valueOf(countscore));
        game.getChildren().add(score);

        //Einfügen von Buttons für Spielfeld
        for(int i=1; i<=3;i++){
            for(int j=1;j<=3;j++){
                Button b = new Button();
                b.setTranslateX(50*j);
                b.setTranslateY(50*i);
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
                        checkGameOver();
                    }
                });
                game.getChildren().add(b);
                buttons.add(b);
            }
        }
        primaryStage.show();
    }

    public void checkGameOver(){
        boolean gameover = false;

        //Check if X wins
        for(int i=0; i<=2;i++){
            if(buttons.get(i).getText() == "X" && buttons.get(i+3).getText() == "X" && buttons.get(i+6).getText() == "X") {
                alert.setText("X wins");
                gameover = true;
                if(streakO == true){
                    streak = false;
                    streakO = false;
                }
                streakX = true;
                countscore++;
            }
        }
        for(int i=0;i<=6;i+=3){
            if(buttons.get(i).getText() == "X" && buttons.get(i+1).getText() == "X" && buttons.get(i+2).getText() == "X"){
                alert.setText("X wins");
                gameover = true;
                if(streakO == true){
                    streak = false;
                    streakO = false;
                }
                streakX = true;
                countscore++;
            }
        }
        if(buttons.get(0).getText() == "X" && buttons.get(4).getText() == "X" && buttons.get(8).getText() == "X") {
            alert.setText("X wins");
            gameover = true;
            if(streakO == true){
                streak = false;
                streakO = false;
            }
            streakX = true;
            countscore++;
        }
        else if(buttons.get(2).getText() == "X" && buttons.get(4).getText() == "X" && buttons.get(6).getText() == "X"){
            alert.setText("X wins");
            gameover = true;
            if(streakO == true){
                streak = false;
                streakO = false;
            }
            streakX = true;
            countscore++;
        }

        //Check if O wins
        for(int i=0; i<=2;i++){
            if(buttons.get(i).getText() == "O" && buttons.get(i+3).getText() == "O" && buttons.get(i+6).getText() == "O") {
                alert.setText("O wins");
                gameover = true;
                if(streakX == true){
                    streak = false;
                    streakX = false;
                }
                streakO = true;
                countscore++;
            }
        }
        for(int i=0;i<=6;i+=3){
            if(buttons.get(i).getText() == "O" && buttons.get(i+1).getText() == "O" && buttons.get(i+2).getText() == "O"){
                alert.setText("O wins");
                gameover = true;
                if(streakX == true){
                    streak = false;
                    streakX = false;
                }
                streakO = true;
                countscore++;
            }
        }
        if(buttons.get(0).getText() == "O" && buttons.get(4).getText() == "O" && buttons.get(8).getText() == "O") {
            alert.setText("O wins");
            gameover = true;
            if(streakX == true){
                streak = false;
                streakX = false;
            }
            streakO = true;
            countscore++;
        }
        else if(buttons.get(2).getText() == "O" && buttons.get(4).getText() == "O" && buttons.get(6).getText() == "O"){
            alert.setText("O wins");
            gameover = true;
            if(streakX == true){
                streak = false;
                streakX = false;
            }
            streakO = true;
            countscore++;
        }

        //Check if tie
        int counter = 0;
        for(Button b : buttons){
            if(b.getText() != "") counter++;
            if(counter == 9) gameover = true;
        }

        //Count the Streak
        System.out.println("streak: "+String.valueOf(streak));
        System.out.println("streakX: "+String.valueOf(streakX));
        System.out.println("streakO: "+String.valueOf(streakO));
        System.out.println();
        if(streak) {
            score.setText(String.valueOf(countscore));
        }
        if(!streak){
            Highscore();
            streak = true;
            countscore = 0;
        }

        //Check if game is over
        if(gameover){
            for(Button b : buttons){
                b.setDisable(true);
            }
        }
    }

    public void Highscore() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Highscore");
            stage.setScene(new Scene(root, 300, 400));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
