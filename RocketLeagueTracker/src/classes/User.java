package classes;

import java.io.FileNotFoundException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Die Klasse "User" speichert alle Informationen zu einem Spieler.
 * Ein User kann sich in der Applikation anmelden und erh‰lt anschlieﬂend alle abgespeicherten Daten in einer Spielerlounge.
 * Es gibt einen Main-User welcher sich anfangs in der Applikation anmeldet und einen Teamkameraden welcher bei der Erstellung einer Session zus‰tlich ausgew‰hlt wird.
 * Somit werden alle gespeicherten Statistiken auch dem Spielerprofil des Mitspielers zugeschrieben.
 */

@Entity 
@Table(name = "user")
public class User {
	
// ------------ INSTANZVARIABLEN -----------
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "idUser")
	private int idUser;
	@Column (name = "player_ID")
	private String player_ID;
	@Column(name = "name")
	private String name;
	@Column(name = "clan")
	private String clan;
	@Column(name = "league")
	private String league;
	@Column(name = "password")
	private String password;
	@Column(name = "profilepicture")
	private String profilepicture;
	@Column(name = "topScorer")
	private int topScorer;
	@Column(name = "topDefender")
	private int topDefender;
	@Column(name = "topWingman")
	private int topWingman;
	@Column(name = "goals")
	private int goals;
	@Column(name = "saves")
	private int saves;
	@Column(name = "assists")
	private int assists;
	@Column(name = "mvp")
	private int mvp; //MVP pro Game
	@Column(name = "sessionMVP")
	private int sessionMVP;

	
// ------------ KONSTRUKTOREN -----------
		
	public User(String player_ID, String name, String clan, String league, String password) throws FileNotFoundException {
		super();
		this.player_ID = player_ID;
		this.name = name;
		this.clan = clan;
		this.league = league;
		this.password = password;

	}
	
	//Defaultkonstruktor
	public User () {};
	
// ------------ GETTER & SETTER -----------	
	

	public int getIdUser() {
		return idUser;
	}

	public String getPlayer_ID() {
		return player_ID;
	}

	public String getName() {
		return name;
	}

	public String getClan() {
		return clan;
	}

	public String getLeague() {
		return league;
	}

	public String getPassword() {
		return password;
	}

	public String getProfilepicture() {
		if(profilepicture == null) profilepicture = ".\\resources\\standardProfilepic.png";
		return profilepicture;
	}

	public int getTopScorer() {
		return topScorer;
	}

	public int getTopDefender() {
		return topDefender;
	}

	public int getTopWingman() {
		return topWingman;
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

	public int getMvp() {
		return mvp;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setPlayer_ID(String player_ID) {
		this.player_ID = player_ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}

	public void setTopScorer(int topScorer) {
		this.topScorer = topScorer;
	}

	public void setTopDefender(int topDefender) {
		this.topDefender = topDefender;
	}

	public void setTopWingman(int topWingman) {
		this.topWingman = topWingman;
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

	public void setMvp(int mvp) {
		this.mvp = mvp;
	}

	public int getSessionMVP() {
		return sessionMVP;
	}
	
	public void setSessionMVP(int sessionMVP) {
		this.sessionMVP = sessionMVP;
	}

	
// ------------ METHODEN -----------

	@Override
	public String toString() {
		return player_ID;
	}
	
}
