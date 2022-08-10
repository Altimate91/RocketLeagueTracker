package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import classes.Game;
import classes.Session;
import classes.User;
import database.ManageSession;
import database.ManageUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.MainFX;

public class PlayerLoungeController implements Initializable{
	
// ----------- INSTANZVARIABLEN -----------
	
	private Stage primaryStage;
	private Parent root;
	private String css = this.getClass().getResource("/view/Stylesheet.css").toExternalForm();
	
	private Session currentSession;
	
	//--- User Daten ---
	@FXML
	private Label headline;
	@FXML
	private Label userID;
	@FXML
	private ImageView imv_profilepic;
	@FXML
	private ImageView imv_leagueLogo;
	@FXML
	private Label lbl_PlayerID;
	@FXML
	private Label lbl_League;
	@FXML
	private Label lbl_PlayerName;
	@FXML
	private Label lbl_clan;
	// ---  ProgressBar ---
	@FXML
	private ProgressBar bar_gamesLimit;
	@FXML
	private Label lbl_gamesLimit_game;
	@FXML
	private Label lbl_BarGamesPlayed;
	@FXML
	private Label lbl_gamesLimit_outOf;
	@FXML
	private Label lbl_gamesMax;
	//--- Session Board ---
	@FXML
	private Label lbl_gamesPlayed;
	@FXML
	private Label lbl_wins;
	@FXML
	private Label lbl_defeats;
	@FXML
	private Label lbl_goals;
	@FXML
	private Label lbl_received;
	@FXML
	private Label lbl_record;
	// --- Table ----
	@FXML
	private TableView<Game> tbl_gamesTable;	
	@FXML
	private TableColumn<Game, Integer> col_game;
	@FXML
	private TableColumn<Game, String> col_result;
	@FXML
	private TableColumn<Game, String> col_score;
	@FXML
	private TableColumn<Game,String> col_mvp;	
	//---- Buttons ---
	@FXML
	private Button btn_newSession;
	@FXML
	private Button btn_newGame;
	@FXML
	private Button btn_closeSession;
	
// ----------- GETTER & SETTER -----------	


	
	
// ----------- METHODEN -----------
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Schaut ob in dieser Sitzung bereits eine Session erstellt worden ist (Sonst l�dt die Methode w�rte von der letzten Sitzung aus Datenbank)
		if(MainFX.getCurrentSession() !=null) currentSession = ManageSession.getCurrentSession();
		
	
		System.out.println("Initialize Method is called");

		//Userdaten in Label laden
		try {
		User user = MainFX.getMainUser();	
		this.imv_profilepic.setImage(new Image(new FileInputStream(new File(user.getProfilepicture()))));
		this.lbl_PlayerID.setText(user.getPlayer_ID());
		this.lbl_League.setText(user.getLeague().toString());
		this.lbl_PlayerName.setText(user.getName());
		this.lbl_clan.setText(user.getClan());
		this.getLeagueLogo();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Stats in Session Board aktualisieren
		if(currentSession != null) {
			//�bersicht wird mit werten aktualisiert
			if(currentSession.getGamelist() != null) {
				this.lbl_gamesPlayed.setVisible(true);
				this.lbl_gamesPlayed.setText(Integer.toString(currentSession.gamesPlayed()));
				this.lbl_wins.setVisible(true);
				this.lbl_wins.setText(Integer.toString(currentSession.wins()));
				this.lbl_defeats.setVisible(true);
				this.lbl_defeats.setText(Integer.toString(currentSession.defeats()));
				this.lbl_goals.setVisible(true);
				this.lbl_goals.setText(Integer.toString(currentSession.goals()));
				this.lbl_received.setVisible(true);
				this.lbl_received.setText(Integer.toString(currentSession.received()));
				this.lbl_record.setVisible(true);
				this.lbl_record.setText(showRecord());
				//nach dem hinzuf�gen eines Games wird der Balken aktualisiert und verl�ngert
				this.bar_gamesLimit.setProgress(currentSession.gamesPlayed() / Double.valueOf(currentSession.getGameLimit()));
			}
		}
		
		//GAMELIMIT BAR
		//wenn ein Game Limit eingegeben wurde wird BarLeiste angezeigt & aktualisiert	
		if(currentSession != null) {
			 System.out.println(currentSession.toString());
			//Bar & Labels werden sichtbar
			this.bar_gamesLimit.setVisible(true);
			this.lbl_gamesLimit_game.setVisible(true);
			this.lbl_BarGamesPlayed.setVisible(true);
			this.lbl_gamesLimit_outOf.setVisible(true);
			this.lbl_gamesMax.setVisible(true);
			//Labels der bar werden mit Werten bef�llt
			this.lbl_gamesMax.setText(Integer.toString(currentSession.getGameLimit()));
			this.lbl_BarGamesPlayed.setText(Integer.toString(currentSession.getGamesPlayed()));
		}
		
		//wenn GameLimit erreicht ist soll dem User eine Meldung ausgegeben werden
		if(currentSession != null && currentSession.getGamesPlayed() == currentSession.getGameLimit()) {
			new Alert(Alert.AlertType.INFORMATION, "Your reached your GameLimit of " + Integer.toString(currentSession.getGameLimit())).showAndWait();
		}
		
		
		
		//TableView bef�llen
		if(currentSession != null) {
			//�bersicht wird mit werten aktualisiert
			if(currentSession.getGamelist() != null) {
				col_game.setCellValueFactory(new PropertyValueFactory<Game, Integer>("gameNo"));
				col_result.setCellValueFactory(new PropertyValueFactory<Game, String>("result"));
				col_score.setCellValueFactory(new PropertyValueFactory<Game, String>("score"));
				col_mvp.setCellValueFactory(new PropertyValueFactory<Game, String>("mvpName"));
				tbl_gamesTable.setItems(MainFX.getOlGames());
			}
			
		}
		

		
		
	}



@FXML
public void openPlayerLounge(ActionEvent event) throws FileNotFoundException {
	
	primaryStage = MainFX.getStage();
		
	try {
		root = FXMLLoader.load(getClass().getResource("/view/PlayerLounge.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(css);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(MainFX.getIcon());
		primaryStage.setTitle("PlayerLounge");
		primaryStage.show();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	System.out.println("--- User " + MainFX.getMainUser().getPlayer_ID() + " entered PlayerLounge ---");	

}



@FXML
public void openMyStatsDialog (ActionEvent event) {
	
	try {
		
		root = FXMLLoader.load(getClass().getResource("/view/UserStatsDialog.fxml"));
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle(MainFX.getMainUser().getName() + " Statistic");
		
		System.out.println("--- Opened User Stats Dialog ---");
		
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
		Node btnClose = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
		btnClose.managedProperty().bind(btnClose.visibleProperty());
		btnClose.setVisible(false);
		
		dialog.showAndWait();
	
	} catch (IOException e) {
		e.printStackTrace();
	}			
}


@FXML
public void openArchiveDialog (ActionEvent event) {
	
	try {
		
		root = FXMLLoader.load(getClass().getResource("/view/SessionArchiveDialog.fxml"));
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle("Session Archive");
		
		System.out.println("--- Opened Archive Dialog ---");
				
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
		Node btnClose = dialog.getDialogPane().lookupButton(ButtonType.CLOSE);
		btnClose.managedProperty().bind(btnClose.visibleProperty());
		btnClose.setVisible(false);
		
		dialog.showAndWait();
		
	
	} catch (IOException e) {
		e.printStackTrace();
	}			
}


@FXML
public void openEditProfileDialog (ActionEvent event) {
	
	
	
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditUserProfileDialog.fxml"));
		root = loader.load();
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle("Edit User-Profile");        
		System.out.println("--- Opened Edit User Profile Dialog ---");
		
		ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
		
		Optional<ButtonType> response = dialog.showAndWait();
		if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
			
			EditUserProfileDialogController editUserController = loader.getController();
			editUserController.submitEditedPlayer(event);
			//update PlayerLounge
			System.out.println("--- back to PlayerLounge ---");
			this.initialize(null,null);
		}			
	
	} catch (IOException e) {
		e.printStackTrace();
	}		
}


@FXML
public void openNewSessionDialog (ActionEvent event) {
	
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewSessionDialog.fxml"));
		root = loader.load();
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle("New Game");
		System.out.println("--- Opened New Session Dialog ---");
		
		ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
		
		Optional<ButtonType> response = dialog.showAndWait();
		if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
			
			NewSessionDialogController sessionController = loader.getController();
			sessionController.submit(event);
			System.out.println("--- New active Session ---");
			MainFX.setCurrentSession(ManageSession.getCurrentSession());
			
			this.btn_newSession.setDisable(true);
			this.btn_newGame.setDisable(false);
			this.btn_closeSession.setDisable(false);
			
			System.out.println("--- back to PlayerLounge ---");
			this.initialize(null, null);
		}	
		
	
	} catch (IOException e) {
		e.printStackTrace();
	}
}

@FXML
public void openNewGameDialog (ActionEvent event) {
	
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewGameDialog.fxml"));
		root = loader.load();
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle("New Game");
		System.out.println("--- Opened New Game Dialog ---");
		
		ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
		
		Optional<ButtonType> response = dialog.showAndWait();
		if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
			
			NewGameDialogController gameController = loader.getController();
			gameController.submitStats(event);
			//update PlayerLounge
			System.out.println("--- back to PlayerLounge ---");
			this.initialize(null, null);
		}	
		
	
	} catch (IOException e) {
		e.printStackTrace();
	}			
}


@FXML
public void closeSession (ActionEvent event) {
	
	Alert alert = new Alert (AlertType.WARNING);
	alert.setTitle("Close Session");
	alert.setHeaderText("You're about to close your current Session!");
	alert.setContentText("Do you really want to close this Session?");
	
	ButtonType btnNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
	
	alert.getDialogPane().getButtonTypes().addAll(btnNo);
	
	Optional<ButtonType> response = alert.showAndWait();
	if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {

		//Werte updaten
		currentSession.setGamesPlayed(currentSession.gamesPlayed());
		currentSession.setGoalsScored(currentSession.goals());
		currentSession.setGoalsReceived(currentSession.received());
		currentSession.setWins(currentSession.wins());
		currentSession.setDefeats(currentSession.defeats());
		currentSession.setSaves(currentSession.savesByPlayer1() + currentSession.savesByPlayer2());
		currentSession.setAssists(currentSession.assistsByPlayer1() + currentSession.assistsByPlayer2());
		currentSession.setRecord(this.showRecord());
		//topWerte auswerten & updaten
		currentSession.setTopScorer(currentSession.sessionTopScorer().getPlayer_ID());
		currentSession.setTopDefender(currentSession.sessionTopDefender().getPlayer_ID());
		currentSession.setTopWingman(currentSession.sessionTopWingman().getPlayer_ID());
		currentSession.setSessionMVP(currentSession.sessionMVP());
			//Session-MVP Werte an User Table in DB �bergeben
			if(currentSession.getSessionMVP().equals(currentSession.getPlayer1())) {
				currentSession.getPlayer1().setSessionMVP(currentSession.getPlayer1().getSessionMVP() +1);
				ManageUser.update(currentSession.getPlayer1());
			}
			else if (currentSession.getSessionMVP().equals(currentSession.getPlayer2())) {
				currentSession.getPlayer2().setSessionMVP(currentSession.getPlayer2().getSessionMVP() +1);
				ManageUser.update(currentSession.getPlayer2());
			}
		
		//session-Werte in DB updaten
		ManageSession.update(currentSession);
			
		
	//Werte auf Standard zur�ck setzen
		//Buttons aus- bzw. einblenden
		btn_newGame.setDisable(true);
		btn_closeSession.setDisable(true);
		btn_newSession.setDisable(false);
		
		//Labels in Statistic Board ausblenden
		this.lbl_gamesPlayed.setVisible(false);
		this.lbl_wins.setVisible(false);
		this.lbl_defeats.setVisible(false);
		this.lbl_goals.setVisible(false);
		this.lbl_received.setVisible(false);
		this.lbl_record.setVisible(false);
		// ProgressBar ausblenden
		this.bar_gamesLimit.setVisible(false);
		this.lbl_gamesLimit_game.setVisible(false);
		this.lbl_BarGamesPlayed.setVisible(false);
		this.lbl_gamesLimit_outOf.setVisible(false);
		this.lbl_gamesMax.setVisible(false);
		//tableView leeren
		MainFX.getOlGames().clear();
	}
	
	System.out.println(" *** Session closed *** ");

}



@FXML
public void openSessionStatsDialog (ActionEvent event) {
	
	try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SessionStatsDialog.fxml"));
		root = loader.load();
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.getDialogPane().setContent(root);
		dialog.getDialogPane().getStylesheets().add(css);
		dialog.setTitle("SessionStats");
		System.out.println("--- Opened SessionStats Dialog ---");
	
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnClose);
		dialog.showAndWait();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}


@FXML
public void logout (ActionEvent event) {
		
		primaryStage = MainFX.getStage();
		
		//zur�ck zur Startmaske
		try {
			root = FXMLLoader.load(getClass().getResource("/view/Start.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(css);
			primaryStage.setScene(scene);
			primaryStage.setTitle("RocketLeagueTracker");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("--- User " + MainFX.getMainUser().getPlayer_ID() + " entered PlayerLounge ---");	

	

		
			
}


// ---- Methode f�r Initialize ----
	private String showRecord() {
		
		int wins = Integer.parseInt(lbl_wins.getText());
		int defeats = Integer.parseInt(lbl_defeats.getText());
		
		StringBuilder sb = new StringBuilder();
		//wenn mehr Siege als Niederlagen
		if(wins > defeats) {
			sb.append("W");
			lbl_record.setTextFill(Color.GREEN);
		} //wenn mehr Niederlagen als Siege
		else if (defeats > wins) {
			sb.append("L");
			lbl_record.setTextFill(Color.RED);
		}
		else {
			sb.append("even");
			lbl_record.setTextFill(Color.YELLOW);
		}
		
		sb.append((Integer) Math.abs(wins - defeats));
		
	return sb.toString();
	}
	
	private void getLeagueLogo() { // Add: Methode zu Static �ndern und gleich das Logo in imageView eintragen und die Schriftart von lbl_Leaggue �ndern

		switch(MainFX.getMainUser().getLeague()) {
		case "UNRANKED" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\unranked.PNG")); this.lbl_League.setTextFill(Color.LIGHTGREY);
			break;
		case "BRONZE_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\BronzeI.PNG")); this.lbl_League.setTextFill(Color.PERU);
			break;
		case "BRONZE_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\BronzeII.PNG")); this.lbl_League.setTextFill(Color.PERU);
			break;
		case "BRONZE_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\BronzeIII.PNG")); this.lbl_League.setTextFill(Color.PERU);
			break;
		case "SILVER_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\SilverI.PNG"));
			break;
		case "SILVER_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\SilverII.PNG"));
			break;
		case "SILVER_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\SilverIII.PNG"));
			break;
		case "GOLD_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\GoldI.PNG"));
			break;
		case "GOLD_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\GoldII.PNG"));
			break;
		case "GOLD_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\GoldIII.PNG"));
			break;
		case "PLATIN_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\PlatinI.PNG"));
			break;
		case "PLATIN_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\PlatinII.PNG"));
			break;
		case "PLATIN_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\PlatinIII.PNG"));
			break;
		case "DIAMOND_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\DiamantI.PNG"));
			break;
		case "DIAMOND_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\DiamantII.PNG"));
			break;
		case "DIAMOND_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\DiamantIII.PNG"));
			break;
		case "CHAMMPION_I" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\ChampionI.PNG"));
			break;
		case "CHAMPION_II" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\ChampionII.PNG"));
			break;
		case "CHAMPION_III" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\ChampionIII.PNG"));
			break;
		case "GRANDCHAMPION" : imv_leagueLogo.setImage(new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\GrandChampion.PNG"));
			break;	
		}
		
	}


}
