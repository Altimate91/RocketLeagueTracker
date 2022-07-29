package classes;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Session {
	
// ------------ INSTANZVARIABLEN -----------
	
	@Column
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int session_ID;
	@Column(name = "date")
	private LocalDateTime date;
	@Column(name = "record")
	private String record;
	@Column(name = "player1")
	private User player1;
	@Column(name = "player2")
	private User Player2;
	@Column(name = "goalsScored")
	private int goalsScored;
	@Column(name = "gaolsReceived")
	private int goalsReceived;
	@Column(name = "gamesPlayed")
	private int gamesPlayed;
	@Column(name = "wins")
	private int wins;
	@Column(name = "Defeats")
	private int defeats;
	@Column(name = "topScorer")
	private String topScorer;
	@Column(name = "topDefender")
	private String topDefender;
	@Column(name = "topWingman")
	private String topWingman;
	@Column(name = "sessionMVP")
	private User sessionMVP;
	
	private int gameLimit; //Limit welches sich User in "NewSessionDialog" setzt
	private ArrayList<Game> gamelist;
	
	
// ------------ KONSTRUKTOREN -----------
	
	public Session() {}
	

	
// ------------ GETTER & SETTER -----------
		

	public int getSession_ID() {
		return session_ID;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getRecord() {
		return record;
	}

	public User getPlayer1() {
		return player1;
	}

	public User getPlayer2() {
		return Player2;
	}

	public int getGoalsScored() {
		return goalsScored;
	}

	public int getGoalsReceived() {
		return goalsReceived;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public int getDefeats() {
		return defeats;
	}

	public User getSessionMVP() {
		return sessionMVP;
	}

	public int getGameLimit() {
		return gameLimit;
	}

	public ArrayList<Game> getGamelist() {
		return gamelist;
	}

	public String getTopScorer() {
		return topScorer;
	}

	public String getTopDefender() {
		return topDefender;
	}

	public String getTopWingman() {
		return topWingman;
	}

	public void setSession_ID(int session_ID) {
		this.session_ID = session_ID;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public void setPlayer2(User player2) {
		Player2 = player2;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public void setGoalsReceived(int goalsReceived) {
		this.goalsReceived = goalsReceived;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}

	public void setSessionMVP(User sessionMVP) {
		this.sessionMVP = sessionMVP;
	}

	public void setGameLimit(int gameLimit) {
		this.gameLimit = gameLimit;
	}

	public void setGamelist(ArrayList<Game> gamelist) {
		this.gamelist = gamelist;
	}
	
	public void setTopScorer(String topScorer) {
		this.topScorer = topScorer;
	}

	public void setTopDefender(String topDefender) {
		this.topDefender = topDefender;
	}

	public void setTopWingman(String topWingman) {
		this.topWingman = topWingman;
	}
	


// ------------ METHODEN -----------

	public int gamesPlayed() {
		for(Game aGame : gamelist) {
			gamesPlayed = gamelist.size();
		}
		return gamesPlayed;
	}
	
	public int wins() {
		int wins = 0;
		for(Game aGame : gamelist) {
			if(aGame.getResult().equals("Win")) {
				wins += 1;
			}
		}
		return wins;
	}
	
	public int defeats() {
		int defeats = 0;
		for(Game aGame : gamelist) {
			if(aGame.getResult().equals("Defeat")) {
				defeats += 1;
			}
		}
		return defeats;
	}
	
	public int goals() {
		int goals = 0;
		for(Game aGame : gamelist) {
			goals += aGame.getGoalsScored();
		}
		return goals;
	}
	
	public int received() {
		int received = 0;
		for(Game aGame : gamelist) {
			received += aGame.getGoalsReceived();
		}
		return received;
		
	}
	
	public String topScorer() {
		String topScorer = null;
		for(Game aGame : gamelist) {
			if(aGame.getPlayer1Statistic().getGoals() > aGame.getPlayer2Statistic().getGoals()) {
				topScorer = aGame.getPlayer1Statistic().getPlayer();
			}
			else if(aGame.getPlayer2Statistic().getGoals() > aGame.getPlayer1Statistic().getGoals()) {
				topScorer = aGame.getPlayer2Statistic().getPlayer();
			}
			else if(aGame.getPlayer1Statistic().getGoals() == aGame.getPlayer2Statistic().getGoals()) {
				topScorer = "even";
			}
		}
	return topScorer;	
	}
	
	public String topDefender() {
		String topDefender = null;
		for(Game aGame : gamelist) {
			if(aGame.getPlayer1Statistic().getSaves() > aGame.getPlayer2Statistic().getSaves()) {
				topDefender = aGame.getPlayer1Statistic().getPlayer();
			}
			else if(aGame.getPlayer2Statistic().getSaves() > aGame.getPlayer1Statistic().getSaves()) {
				topDefender = aGame.getPlayer2Statistic().getPlayer();
			}
			else if(aGame.getPlayer1Statistic().getSaves() == aGame.getPlayer2Statistic().getSaves()) {
				topDefender = "even";
			}
		}
	return topDefender;	
	}
	
	public String topWingman() {
		String topWingman = null;
		for(Game aGame : gamelist) {
			if(aGame.getPlayer1Statistic().getSaves() > aGame.getPlayer2Statistic().getSaves()) {
				topWingman = aGame.getPlayer1Statistic().getPlayer();
			}
			else if(aGame.getPlayer2Statistic().getSaves() > aGame.getPlayer1Statistic().getSaves()) {
				topWingman = aGame.getPlayer2Statistic().getPlayer();
			}
			else if(aGame.getPlayer1Statistic().getSaves() == aGame.getPlayer2Statistic().getSaves()) {
				topWingman = "even";
			}
		}
	return topWingman;	
	}

	@Override
	public String toString() {
		return "Session [date=" + date + ", record=" + record + ", player1=" + player1
				+ ", Player2=" + Player2 + ", goalsScored=" + goalsScored + ", goalsReceived=" + goalsReceived
				+ ", gamesPlayed=" + gamesPlayed + ", wins=" + wins + ", defeats=" + defeats + ", sessionMVP="
				+ sessionMVP + ", gameLimit=" + gameLimit + "]";
	}
	
	
} // END OF SESSION-CLASS
