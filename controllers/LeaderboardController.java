package controllers;
/*
*Last updated on 10/29/20
*
*Controls the links and buttons of leaderboards fxml file
*Yes there are two buttons to play game for the user's convenience
*
*Contributing authors
*@author Ryan
*/
import apiarcade.RunApp;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.Leaderboard;
import models.LoginSession;
import models.Score;
import models.User;

public class LeaderboardController  implements Initializable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private JFXButton _btnPlayNow;
    @FXML
    private Label game1user1;
    @FXML
    private Label game1user2;
    @FXML
    private Label game1user3;
    @FXML
    private Label game1score1;
    @FXML
    private Label game1score2;
    @FXML
    private Label game1score3;
    @FXML
    private Label game2user1;
    @FXML
    private Label game2user2;
    @FXML
    private Label game2user3;
    @FXML
    private Label game2score1;
    @FXML
    private Label game2score2;
    @FXML
    private Label game2score3;
    @FXML
    private Label userID;
    @FXML
    private Label currentGame1Score;
    @FXML
    private Label currentGame2Score;

    private User user = LoginSession.currentUser;
    private Leaderboard lb = new Leaderboard();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //get high scores
        int numOfLabels = 3;
        try{
        Map<String, Integer> highScoresGameOne = lb.getHighScores(lb.getGame1ID(), numOfLabels);
        Map<String, Integer> highScoresGameTwo = lb.getHighScores(lb.getGame2ID(), numOfLabels);

        //get strings for tgame 1 labels
        String[] keySet = new String[numOfLabels];
        int iterator = 0;
        for(String key : highScoresGameOne.keySet()){
            keySet[iterator] = key;
            iterator++;
        }
        //populate labels
        game1user1.setText(keySet[0]);
        game1user2.setText(keySet[1]);
        game1user3.setText(keySet[2]);
        game1score1.setText("" + highScoresGameOne.get(game1user1.getText()));
        game1score2.setText("" + highScoresGameOne.get(game1user2.getText()));
        game1score3.setText("" + highScoresGameOne.get(game1user3.getText()));

        //get strings for game 2 labels
        iterator = 0;
        for(String key : highScoresGameTwo.keySet()){
            keySet[iterator] = key;
            iterator++;
        }
        game2user1.setText(keySet[0]);
        game2user2.setText(keySet[1]);
        game2user3.setText(keySet[2]);
        game2score1.setText("" + highScoresGameTwo.get(game2user1.getText()));
        game2score2.setText("" + highScoresGameTwo.get(game2user2.getText()));
        game2score3.setText("" + highScoresGameTwo.get(game2user3.getText()));

        }
        catch(IOException e){
            System.out.println(e.toString());
        }

        //initialize personalized labels
        userID.setText("Hello! " + user.getUsername());
        try {
            currentGame1Score.setText("Your Score: " + Score.getScoreByUsername(user.getUsername(), lb.getGame1ID()));
            currentGame2Score.setText("Your Score: " + Score.getScoreByUsername(user.getUsername(), lb.getGame2ID()));
        } catch (Exception ex) {
            Logger.getLogger(LeaderboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentGame2Score.setText("Your Score: " + 0);
    }

    @FXML
    void _mainGamesLink(ActionEvent event) throws IOException {
        _mainGamesPlayNow(event);
    }

    @FXML
    void _mainGamesPlayNow(ActionEvent event) throws IOException {
        RunApp.showMainGameView();
    }

    @FXML
    void _userLogOut(ActionEvent event) throws IOException {
        System.out.println("User logged out");
        RunApp.showLoginView();
    }

    @FXML
    void _viewProfile(ActionEvent event) throws IOException {
        System.out.println("Move to profile view");
        //RunApp.showProfileView();
    }




}
