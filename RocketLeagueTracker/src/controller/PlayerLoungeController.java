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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
		
		//Schaut ob in dieser Sitzung bereits eine Session erstellt worden ist (Sonst lädt die Methode wärte von der letzten Sitzung aus Datenbank)
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
		this.imv_leagueLogo.setImage(user.getLeagueLogo());
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Stats in Session Board aktualisieren
		if(currentSession != null) {
			//Übersicht wird mit werten aktualisiert
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
				//nach dem hinzufügen eines Games wird der Balken aktualisiert und verlängert
				this.bar_gamesLimit.setProgress(currentSession.gamesPlayed() / Double.valueOf(currentSession.getGameLimit()));
			}
		}
		
		//GAMELIMIT BAR
		//wenn ein Game Limit eingegeben wurde wird BarLeiste angezeigt & aktualisiert	
		if(currentSession != null) {
			//Bar & Labels werden sichtbar
			this.bar_gamesLimit.setVisible(true);
			this.lbl_gamesLimit_game.setVisible(true);
			this.lbl_BarGamesPlayed.setVisible(true);
			this.lbl_gamesLimit_outOf.setVisible(true);
			this.lbl_gamesMax.setVisible(true);
			//Labels der bar werden mit Werten befüllt
			this.lbl_gamesMax.setText(Integer.toString(currentSession.getGameLimit()));
			this.lbl_BarGamesPlayed.setText(Integer.toString(currentSession.getGamesPlayed()));
		}
		
		//TableView befüllen
		if(currentSession != null) {
			//Übersicht wird mit werten aktualisiert
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
		dialog.showAndWait();
		
		System.out.println("--- Opened User Stats Dialog ---");
		
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnClose);
		
	
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
		dialog.showAndWait();
		
		System.out.println("--- Opened Archive Dialog ---");
		
		ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
		
		dialog.getDialogPane().getButtonTypes().addAll(btnClose);
		
	
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
		//topWerte auswerten
		Session currentSession = MainFX.getSessionList().get(MainFX.getSessionList().size() -1);
		currentSession.setTopScorer(currentSession.topScorer());
		currentSession.setTopDefender(currentSession.topDefender());
		currentSession.setTopWingman(currentSession.topWingman());
		
		//session-Werte in DB updaten
		ManageSession.update(currentSession);
		
		
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
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}


@FXML
public void logout (ActionEvent event) {
		
		primaryStage = MainFX.getStage();
		
		//zurück zur Startmaske
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


// ---- Methode für Initialize ----
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


}
