package classes;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Game {
	
// ----------- INSTANZVARIABLEN -----------
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gameID;
	@Column(name="sessionID")
	private int sessionID;
	@Column(name = "gameNo")
	private int gameNo;
	@Column(name = "result")
	private String result;
	@Column(name = "score")
	private String score;
	@Column(name = "goalsScored")
	private int goalsScored;
	@Column(name = "goalsReceived")
	private int goalsReceived;
	@Column(name = "gameMVP")
	private String gameMVP;
	
	private PlayerStatistic player1Statistic;
	private PlayerStatistic player2Statistic;
	

// ----------- KONSTRUKTOREN -----------
	
	public Game(int gameNo, String result, String score, int goalsScored,
			int goalsReceived, String gameMVP) {
		super();
		this.gameNo = gameNo;
		this.result = result;
		this.score = score;
		this.goalsScored = goalsScored;
		this.goalsReceived = goalsReceived;
		this.gameMVP = gameMVP;
	}

	
	//Defaultkonstruktor
	public Game() {}


	
// ----------- GETTER & SETTER -----------
	
	
	public int getGameID() {
		return gameID;
	}
	
	
	public int getGameNo() {
		return gameNo;
	}
	
	
	public String getResult() {
		return result;
	}
	
	
	public String getScore() {
		return score;
	}
	
	
	public int getGoalsScored() {
		return goalsScored;
	}
	
	
	public int getGoalsReceived() {
		return goalsReceived;
	}
	
	
	public String getGameMVP() {
		return gameMVP;
	}
	
	
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
	
	public void setGameNo(int gameNo) {
		this.gameNo = gameNo;
	}
	
	
	public void setResult(String result) {
		this.result = result;
	}
	
	
	public void setScore(String score) {
		this.score = score;
	}
	
	
	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}
	
	
	public void setGoalsReceived(int goalsReceived) {
		this.goalsReceived = goalsReceived;
	}
	
	
	public void setGameMVP(String gameMVP) {
		this.gameMVP = gameMVP;
	}


	public PlayerStatistic getPlayer1Statistic() {
		return player1Statistic;
	}


	public void setPlayer1Statistic(PlayerStatistic player1) {
		this.player1Statistic = player1;
	}


	public PlayerStatistic getPlayer2Statistic() {
		return player2Statistic;
	}


	public void setPlayer2Statistic(PlayerStatistic player2) {
		this.player2Statistic = player2;
	}



// ----------- METHODEN -----------
	
	
}