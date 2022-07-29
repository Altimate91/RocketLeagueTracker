package classes;

public class UserStatistic {
	
	//------------ INSTANZVARIABLEN -----------
	
	private int goals;
	private int saves;
	private int assists;
	private int mvp;
	
	private int topScorer;
	private int topWingman;
	private int topDefender;
	
	//------------ KONSTRUKTOREN -----------
	
	public UserStatistic () {}

	
	//------------ GETTER & SETTER -----------
	
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
	
	public int getTopScorer() {
		return topScorer;
	}
	
	public int getTopWingman() {
		return topWingman;
	}
	
	public int getTopDefender() {
		return topDefender;
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
	
	public void setTopScorer(int topScorer) {
		this.topScorer = topScorer;
	}
	
	public void setTopWingman(int topWingman) {
		this.topWingman = topWingman;
	}
	
	public void setTopDefender(int topDefender) {
		this.topDefender = topDefender;
	};
	

}
