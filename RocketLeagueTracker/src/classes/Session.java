package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "session")
public class Session {
	
// ------------ INSTANZVARIABLEN -----------
	
	@Column (name = "idSession")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSession;
	@Column(name = "date")
	private LocalDateTime date = LocalDateTime.now();
	@Column(name = "record")
	private String record;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player1", referencedColumnName = "idUser")
	private User player1;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player2", referencedColumnName = "idUser")
	private User player2;
	@Column(name = "gamesPlayed")
	private int gamesPlayed;
	@Column(name = "goalsScored")
	private int goalsScored;
	@Column(name = "gaolsReceived")
	private int goalsReceived;
	@Column(name = "saves")
	private int saves;
	@Column(name = "assists")
	private int assists;
	@Column(name = "wins")
	private int wins;
	@Column(name = "defeats")
	private int defeats;
	@Column(name = "topScorer")
	private String topScorer;
	@Column(name = "topDefender")
	private String topDefender;
	@Column(name = "topWingman")
	private String topWingman;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sessionMVP", referencedColumnName = "idUser")
	private User sessionMVP;
	@OneToMany(mappedBy="sessionID", fetch = FetchType.EAGER)
	private Set<Game> gamelist;
	private int gameLimit; //Limit welches sich User in "NewSessionDialog" setzt

	
	
// ------------ KONSTRUKTOREN -----------
	
	public Session() {
		date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
	}

// ------------ GETTER & SETTER -----------
		

	
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
		return player2;
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

	public Set<Game> getGamelist() {
		return gamelist;
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
		this.player2 = player2;
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

	public void setGamelist(Set<Game> gamelist) {
		this.gamelist = gamelist;
	}

	
	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public int getSaves() {
		return saves;
	}
	
	public int getAssists() {
		return assists;
	}
	
	public void setSaves(int saves) {
		this.saves = saves;
	}
	
	public void setAssists(int assists) {
		this.assists = assists;
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
	
	public int goalsByPlayer1() {
		int goals = 0;
		
		for(Game aGame : gamelist) {
			goals += aGame.getPlayer1Statistic().getGoals();
		}
		
		return goals;
	}
	
	public int goalsByPlayer2() {
		int goals = 0;
		
		for(Game aGame : gamelist) {
			goals += aGame.getPlayer2Statistic().getGoals();
		}
		
		return goals;
	}
	
	public int savesByPlayer1() {
		int saves = 0;
		
		for(Game aGame : gamelist) {
			saves += aGame.getPlayer1Statistic().getSaves();
		}
		
		return saves;
	}
	

	public int savesByPlayer2() {
		int saves = 0;
		
		for(Game aGame : gamelist) {
			saves += aGame.getPlayer2Statistic().getSaves();
		}
		
		return saves;
	}
	

	public int assistsByPlayer1() {
		int assists = 0;
		
		for(Game aGame : gamelist) {
			assists += aGame.getPlayer1Statistic().getAssists();
		}
		
		return assists;
	}
	

	public int assistsByPlayer2() {
		int assists = 0;
		
		for(Game aGame : gamelist) {
			assists += aGame.getPlayer2Statistic().getAssists();
		}
		
		return assists;
	}
	
	public User SessionMVP() {
		int mvpP1 = 0;
		int mvpP2 = 0;
		
		User mvp = null;
		
		for(Game aGame : gamelist) {
			if(aGame.getPlayer1Statistic().isGameMVP() == true) mvpP2 += 1;
			if(aGame.getPlayer2Statistic().isGameMVP() == true) mvpP2 += 1;
		}
		
		if(mvpP1 > mvpP2) return player1;
		if(mvpP2 < mvpP2) return player2;
		else return null;
	}
	
	public User sessionTopScorer() {
		int p1 = 0;
		int p2 = 0;
		User topScorer = null;
		for(Game aGame : gamelist) {
			if(aGame.getTopScorer().equals(player1)) p1 += 1;
			else if(aGame.getTopScorer().equals(player2)) p2 += 1;
		}
		
		if(p1 > p2) topScorer = player1;
		if(p2 > p1) topScorer = player2;
		
		return topScorer;
	}

	public User sessionTopWingman() {
		int p1 = 0;
		int p2 = 0;
		User topWingman = null;
		for(Game aGame : gamelist) {
			if(aGame.getTopWingman().equals(player1)) p1 += 1;
			else if(aGame.getTopWingman().equals(player2)) p2 += 1;
		}
		
		if(p1 > p2) topWingman = player1;
		if(p2 > p1) topWingman = player2;
		
		return topWingman;
	}
	
	public User sessionTopDefender() {
		int p1 = 0;
		int p2 = 0;
		User topDefender = null;
		for(Game aGame : gamelist) {
			if(aGame.getTopDefender().equals(player1)) p1 += 1;
			else if(aGame.getTopDefender().equals(player2)) p2 += 1;
		}
		
		if(p1 > p2) topDefender = player1;
		if(p2 > p1) topDefender = player2;
		
		return topDefender;
	}
	
	public List<PlayerStatistic> getPlayerStatisticList() {
		
		ArrayList<PlayerStatistic> sessionStatisticList = new ArrayList<>();
		
		sessionStatisticList.add(new PlayerStatistic(player1, this.goalsByPlayer1(), this.savesByPlayer1(), this.assistsByPlayer1()));
		sessionStatisticList.add(new PlayerStatistic(player2, this.goalsByPlayer2(), this.savesByPlayer2(), this.assistsByPlayer2()));

		return sessionStatisticList;
	}

	@Override
	public String toString() {
		return "Session [idSession=" + idSession + ", date=" + date + ", record=" + record + ", player1=" + player1
				+ ", player2=" + player2 + ", gamesPlayed=" + gamesPlayed + "]";
	}

	
	
} // END OF SESSION-CLASS
