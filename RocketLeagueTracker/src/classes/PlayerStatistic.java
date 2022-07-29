package classes;

public class PlayerStatistic {
	
// ----------- INSTANZVARIABLEN -----------
	
	private String player;
	private int goals;
	private int saves;
	private int assists;
	
// ----------- KONSTRUKTOREN -----------	
	
	public PlayerStatistic(String player, int goals, int saves, int assists) {
		super();
		this.player = player;
		this.goals = goals;
		this.saves = saves;
		this.assists = assists;
	}

// ----------- GETTER & SETTER -----------
	
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String player) {
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

}
