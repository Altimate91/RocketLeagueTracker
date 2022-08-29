package classes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/*
 * Die PlayerStatistik ist die Spielstatistik eines Spielers in einem einzelnen Game.
 */

@Entity
@Table(name = "playerstatistic")
public class PlayerStatistic {
	
// ----------- INSTANZVARIABLEN -----------
	
	@Column (name = "idPlayerStatistic")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idPlayerStatistic;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "player", referencedColumnName = "idUser")
	private User player;
	@Column(name = "goals")
	private int goals;
	@Column(name = "saves")
	private int saves;
	@Column(name = "assists")
	private int assists;
	@Column(name = "gameMVP")
	private boolean gameMVP;
	
	
	private String playerName;
	
// ----------- KONSTRUKTOREN -----------	
	
	public PlayerStatistic(User player, int goals, int saves, int assists) {
		super();
		this.player = player;
		this.goals = goals;
		this.saves = saves;
		this.assists = assists;
		
		//für Table View
		setPlayerName(player.getName());
	}
	
	public PlayerStatistic() {}

// ----------- GETTER & SETTER -----------
	
	
	public User getPlayer() {
		return player;
	}
	
	public void setPlayer(User player) {
		this.player = player;
	}

	public int getGoals() {
		return goals;
	}


	public int getSaves() {
		return saves;
	}

	public int getAssists() {
		return assists;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public void setSaves(int saves) {
		this.saves = saves;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public boolean isGameMVP() {
		return gameMVP;
	}

	public void setGameMVP(boolean gameMVP) {
		this.gameMVP = gameMVP;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

}
