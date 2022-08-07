package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import classes.Game;
import classes.PlayerStatistic;
import classes.Session;
import classes.User;
import database.ManageGame;
import database.ManagePlayerStatistic;
import database.ManageSession;
import database.ManageUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainFX;

public class NewGameDialogController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------
	
	@FXML
	private Label lbl_player1;
	@FXML
	private Label lbl_player2;	
	@FXML
	private Label lbl_gameNo;	
	@FXML
	private TextField txf_score1;
	@FXML
	private TextField txf_score2;
	@FXML
	private CheckBox cb_MVP_player1;
	@FXML
	private CheckBox cb_MVP_player2;
	@FXML
	private ChoiceBox<String> cb_goals_player1;
	@FXML
	private ChoiceBox<String> cb_goals_player2;
	@FXML
	private ChoiceBox<String> cb_assists_player1;
	@FXML
	private ChoiceBox<String> cb_assists_player2;
	@FXML
	private ChoiceBox<String> cb_saves_player1;
	@FXML
	private ChoiceBox<String> cb_saves_player2;
	
	private Session currentSession;
	
	private int gameNo;
	
	private String[] numbers = {"0","1","2","3","4","5","6","7","8","9","10"};
	
// ----------- METHODEN -----------
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		currentSession = ManageSession.getCurrentSession();
		
		//Spielernamen eintragen
		lbl_player1.setText(currentSession.getPlayer1().getName());
		lbl_player2.setText(currentSession.getPlayer2().getName());
		
		//Auswahlmöglichkeiten für Stats in ChoiceBoxes eintragen
		cb_goals_player1.getItems().addAll(numbers);
		cb_goals_player2.getItems().addAll(numbers);
		cb_assists_player1.getItems().addAll(numbers);
		cb_assists_player2.getItems().addAll(numbers);
		cb_saves_player1.getItems().addAll(numbers);
		cb_saves_player2.getItems().addAll(numbers);
		
		
		//Label mit aktueller Game No. initialisieren	
			if(currentSession.getGamelist() != null) {
				gameNo = currentSession.getGamelist().size() + 1;
			}
			else {
				gameNo = 1;	
			}
			
		lbl_gameNo.setText(Integer.toString(gameNo));
		
	}
	
	//Game speichern und Stats übergeben
	@FXML
	public void submitStats(ActionEvent event) {
		
		String result;
		String score;
		User mvp = null;
		
		int goalsscored = Integer.parseInt(cb_goals_player1.getValue()) + Integer.parseInt(cb_goals_player2.getValue());
		int goalsreceived = Integer.parseInt(txf_score2.getText());
		Game game;
		
		
		//Result ausheben
		if (Integer.parseInt(this.txf_score1.getText()) > Integer.parseInt(this.txf_score2.getText())) {
			result = "Win";
		} else {
			result = "Defeat";
		}
		
		//Score ausheben
		StringBuilder sb = new StringBuilder();
		
		sb.append(txf_score1.getText());
		sb.append(":");
		sb.append(txf_score2.getText());
		score = sb.toString();
		
		//MVP auslesen
		if(cb_MVP_player1.isSelected()) {
			mvp = currentSession.getPlayer1();
		}
		if(cb_MVP_player2.isSelected()) {
			mvp = currentSession.getPlayer2();
		}
		
		
		//neues Game-Objekt erstellen
		game = new Game(currentSession, gameNo, result, sb.toString(), goalsscored, goalsreceived, mvp);
		
		//Game in Session/GameList eintragen
		if(currentSession.getGamelist()!= null) {
		currentSession.getGamelist().add(game);
		} else {
		currentSession.setGamelist(new HashSet<Game> ());
		currentSession.getGamelist().add(game);
		}
		//Game in ObservableList für TableView eintragen
		MainFX.getOlGames().add(game);
		
		System.out.println("*** Game No." + Integer.toString(game.getGameNo()) + " (Result: " + result + ", Score:" + score + ", Goals: " + Integer.toString(goalsscored) + ", Received: " + Integer.toString(goalsreceived) + ", MVP: " + game.getGameMVP() + ") was saved.");

		//GameStats hinzufügen
		//User1
		int goals_p1 = Integer.parseInt(cb_goals_player1.getValue());
		int saves_p1 = Integer.parseInt(cb_saves_player1.getValue());;
		int assists_p1 = Integer.parseInt(cb_assists_player1.getValue());;
		
		game.setPlayer1Statistic(new PlayerStatistic(currentSession.getPlayer1(),goals_p1,saves_p1,assists_p1));
		
		//User1
		int goals_p2 = Integer.parseInt(cb_goals_player2.getValue());
		int saves_p2 = Integer.parseInt(cb_saves_player2.getValue());;
		int assists_p2 = Integer.parseInt(cb_assists_player2.getValue());;
				
		game.setPlayer2Statistic(new PlayerStatistic(currentSession.getPlayer2(),goals_p2,saves_p2,assists_p2));
		
		
		//top-Werte übergeben
		if(goals_p1 > goals_p2) {
			game.setTopScorer(currentSession.getPlayer1());
		} else if (goals_p1 < goals_p2) {
			game.setTopScorer(currentSession.getPlayer2());
		} else { game.setTopScorer(null); };
		
		if(assists_p1 > assists_p2) {
			game.setTopWingman(currentSession.getPlayer1());
		} else if (assists_p1 < assists_p2) {
			game.setTopWingman(currentSession.getPlayer2());
		} else { game.setTopWingman(null); };
		
		if(saves_p1 > saves_p2) {
			game.setTopDefender(currentSession.getPlayer1());
		} else if (saves_p1 < saves_p2) {
			game.setTopDefender(currentSession.getPlayer2());
		} else { game.setTopDefender(null); };
		
		
		//Stats dem MainUser Profil übergeben
		MainFX.getMainUser().setGoals(MainFX.getMainUser().getGoals() + goals_p1);
		MainFX.getMainUser().setSaves(MainFX.getMainUser().getSaves() + saves_p1);
		MainFX.getMainUser().setAssists(MainFX.getMainUser().getAssists() + assists_p1);
		
		
		System.out.println("* Player 1 Stats: " + cb_goals_player1.getValue().toString() + " Goals, " + cb_assists_player1.getValue().toString() + " Assists, " + cb_saves_player1.getValue().toString() + " Saves.");
		System.out.println("* Player 2 Stats: " + cb_goals_player2.getValue().toString() + " Goals, " + cb_assists_player2.getValue().toString() + " Assists, " + cb_saves_player2.getValue().toString() + " Saves.");
		
		//Statistic in DB Tabelle PlayerStatistic eintragen
		//Player 1 Statistic
		ManagePlayerStatistic.saveStatistic(game.getPlayer1Statistic());
		//Player 2 Statistic
		ManagePlayerStatistic.saveStatistic(game.getPlayer2Statistic());
	
		
		//Game in DB eintragen
		ManageGame.saveGame(game);
	
	
	}
		

	
	

}
