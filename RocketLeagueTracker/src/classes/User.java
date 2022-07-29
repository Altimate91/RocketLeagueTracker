package classes;

import java.io.FileNotFoundException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.scene.image.Image;

@Entity
@Table(name = "user")
public class User {
	
// ------------ INSTANZVARIABLEN -----------
	
	@Id
	@GeneratedValue
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
	private int mvp;
	
	private static Image leagueLogo;
	
	
//	@Column(name = "statistic")
//	private UserStatistic statistic;
	
// ------------ KONSTRUKTOREN -----------
		
	public User(String player_ID, String name, String clan, String league, String password) throws FileNotFoundException {
		super();
		this.player_ID = player_ID;
		this.name = name;
		this.clan = clan;
		this.league = league;
		this.password = password;
		
		//richtiges LeagueLogo in Image eintragen
		switch(league.toString()) {
		case "UNRANKED" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\unranked.PNG"));
			break;
		case "BRONZE_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\BronzeI.PNG"));
			break;
		case "BRONZE_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\BronzeII.PNG"));
			break;
		case "BRONZE_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\BronzeIII.PNG"));
			break;
		case "SILVER_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\SilverI.PNG"));
			break;
		case "SILVER_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\SilverII.PNG"));
			break;
		case "SILVER_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\SilverIII.PNG"));
			break;
		case "GOLD_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\GoldI.PNG"));
			break;
		case "GOLD_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\GoldII.PNG"));
			break;
		case "GOLD_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\GoldIII.PNG"));
			break;
		case "PLATIN_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\PlatinI.PNG"));
			break;
		case "PLATIN_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\PlatinII.PNG"));
			break;
		case "PLATIN_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\PlatinIII.PNG"));
			break;
		case "DIAMOND_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\DiamondI.PNG"));
			break;
		case "DIAMOND_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\DiamondII.PNG"));
			break;
		case "DIAMOND_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\DiamondIII.PNG"));
			break;
		case "CHAMMPION_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\ChampionI.PNG"));
			break;
		case "CHAMPION_II" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\ChampionII.PNG"));
			break;
		case "CHAMPION_III" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\ChampionIII.PNG"));
			break;
		case "GRANDCHAMPION_I" : setLeagueLogo(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\Java\\Eclipse_Workspaces\\Workspace_RL-Tracker\\RocketLeagueTracker\\resources\\GrandChampion.PNG"));
			break;	
		
		}
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
	
	public Image getLeagueLogo() {
		return leagueLogo;
	}

	public void setLeagueLogo(Image leagueLogo) {
		this.leagueLogo = leagueLogo;
	}


		
// ------------ METHODEN -----------
	
	@Override
	public String toString() {
		return "User [player_ID: " + player_ID + ", Name: " + name + ", Clan: " + clan + ", League:" + league.toString()
				+ ", Password: " + password + ", Pfad Profilepicture: " + profilepicture + "]";
	}
	
}
