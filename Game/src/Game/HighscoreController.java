package Game;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HighscoreController {

    @FXML
    private AnchorPane panel;

    @FXML
    private GridPane List;

    @FXML
    private TextArea name = new TextArea();

    @FXML
    private Label finalscore = new Label();

    int score;
    GameController game;

    public HighscoreController(int score){
        this.score = score-1;
    }

    public void initialize() {

        //namefield setzen
        name.setMaxHeight(2);
        name.setMaxWidth(10);
        name.setTranslateX(10);
        name.setTranslateY(5);
        panel.getChildren().add(name);

        //score setzen
        finalscore.setTranslateX(50);
        finalscore.setTranslateY(5);
        finalscore.setText("Highscore: "+String.valueOf(this.score));
        panel.getChildren().add(finalscore);
        checkRank();

        //Database connection herstellen
        

        //List auffülen test(später villeicht löschen)
        for(int i=0;i<10;i++){
            Label t = new Label("facta non verba"+i);
            List.add(t, 1, i);
        }

    }

    public void checkRank() {
        int rank;
        for(int i=9;i<=0;i--){
            //if(db.rank(i).score < score) rank = i;
        }
    }

    public void addToRank(int rank) {
        //UPDATE score, name WHERE HID = rank;
    }
}
