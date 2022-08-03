package classes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Entity
@Table(name = "game")
public class Game {
	
// ----------- INSTANZVARIABLEN -----------
	@Column
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int gameID;
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "gameMVP", referencedColumnName = "idUser")
	private User gameMVP;
	@ManyToOne
	@JoinColumn(name = "sessionID", nullable = false)
	private Session sessionID;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player1Statistic", referencedColumnName = "idPlayerStatistic")
	private PlayerStatistic player1Statistic;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player2Statistic", referencedColumnName = "idPlayerStatistic")
	private PlayerStatistic player2Statistic;
	
	
	private String mvpName; //für TableView

	

// ----------- KONSTRUKTOREN -----------
	
	public Game(Session sessionID, int gameNo, String result, String score, int goalsScored,
			int goalsReceived, User gameMVP) {
		super();
		this.gameNo = gameNo;
		this.result = result;
		this.score = score;
		this.goalsScored = goalsScored;
		this.goalsReceived = goalsReceived;
		this.gameMVP = gameMVP;
		this.sessionID = sessionID;
		
		//für TableView
		setMvpName(gameMVP.getName());
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
	
	
	public User getGameMVP() {
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
	
	
	public void setGameMVP(User gameMVP) {
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


	public Session getSessionID() {
		return sessionID;
	}


	public void setSessionID(Session sessionID) {
		this.sessionID = sessionID;
	}


	public String getMvpName() {
		return mvpName;
	}


	public void setMvpName(String mvpName) {
		this.mvpName = mvpName;
	}



// ----------- METHODEN -----------
	
	
}