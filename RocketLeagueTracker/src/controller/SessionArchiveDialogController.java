package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import classes.Game;
import classes.PlayerStatistic;
import classes.Session;
import classes.User;
import database.ManageSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFX;

public class SessionArchiveDialogController implements Initializable  {
	
	/*
	 * Das Session Archiv bietet eine Übersicht über alle gespielten Sessions eines Spielers.
	 * In einer ListView werden können die Sessions nach Datum ausgewählt werden und die dazugehörigen Daten werden sofort eingeblendet.
	 */
	
	
// ----------- INSTANZVARIABLEN -----------
	
    @FXML
    private Label lbl_assists;
    @FXML
    private Label lbl_defeats;
    @FXML
    private Label lbl_gamesPlayed;
    @FXML
    private Label lbl_goals;
    @FXML
    private Label lbl_received;
    @FXML
    private Label lbl_record;
    @FXML
    private Label lbl_saves;
    @FXML
    private Label lbl_wins;   
    @FXML
    private Label lbl_player1; 
    @FXML
    private Label lbl_player2; 
    @FXML
    private Label lbl_goalsP1;
    @FXML
    private Label lbl_goalsP2;
    @FXML
    private Label lbl_savesP1;
    @FXML
    private Label lbl_savesP2;
    @FXML
    private Label lbl_assistsP1;
    @FXML
    private Label lbl_assistsP2;
    @FXML
    private Label lbl_sessionMVP;
    @FXML
    private ListView<Session> lv_sessionArchive;

// ----------- METHODEN -----------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	//SessionList aus DB in die List View Laden
    	ArrayList<Session> sessionList = (ArrayList<Session>) ManageSession.getSessionList(MainFX.getMainUser().getIdUser());
    	
    	//List View mit gespielten Sessions befüllen
    	lv_sessionArchive.getItems().addAll(sessionList);
    		
    	lv_sessionArchive.setOnMouseClicked(new EventHandler<Event>() {
    	//Wenn eine Session ausgewählt wurde werden dementsprächende Statistiken angezeigt
			@Override
			public void handle(Event arg0) {
				Session selectedSession = lv_sessionArchive.getSelectionModel().getSelectedItem();
			//Session Statistiken befüllen
				lbl_gamesPlayed.setText(Integer.toString(selectedSession.getGamesPlayed()));
				lbl_wins.setText(Integer.toString(selectedSession.getWins()));
				lbl_defeats.setText(Integer.toString(selectedSession.getDefeats()));
				lbl_record.setText(selectedSession.getRecord());
				lbl_goals.setText(Integer.toString(selectedSession.getGoalsScored()));
				lbl_saves.setText(Integer.toString(selectedSession.getSaves()));
				lbl_assists.setText(Integer.toString(selectedSession.getAssists()));
				lbl_received.setText(Integer.toString(selectedSession.getGoalsReceived()));
			//Player Statistiken befüllen
				//Spielernamen
				lbl_player1.setText(selectedSession.getPlayer1().getName());
				lbl_player2.setText(selectedSession.getPlayer2().getName());
				//Tore
				lbl_goalsP1.setText(Integer.toString(selectedSession.goalsByPlayer1()));
				lbl_goalsP2.setText(Integer.toString(selectedSession.goalsByPlayer2()));
				//Assists
				lbl_assistsP1.setText(Integer.toString(selectedSession.assistsByPlayer1()));
				lbl_assistsP2.setText(Integer.toString(selectedSession.assistsByPlayer2()));
				//Saves	
				lbl_savesP1.setText(Integer.toString(selectedSession.savesByPlayer1()));
				lbl_savesP2.setText(Integer.toString(selectedSession.savesByPlayer2()));
			//Label mit SessionMVP befüllen (meiste MVP Titel innerhalb einer Session)
				if(selectedSession.sessionMVP() != null) lbl_sessionMVP.setText(selectedSession.sessionMVP().getName()); else lbl_sessionMVP.setText("");
			}
    		
    	});
    	
    	
    	//Verändert den angezeigten String in der List View (sonst würde er einfach den Hashcode der Datenbank ausgeben)
    	lv_sessionArchive.setCellFactory(param -> new ListCell<Session>() {
            @Override
            protected void updateItem(Session session, boolean empty) {
                super.updateItem(session, empty);

                if (empty || session == null || session.getPlayer1() == null || session.getRecord() == null) {
                    setText(null);
                } else {
                	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                	String dateString = format.format(session.getDate());
                  	
                    setText(dateString + "  |   " + session.getRecord() + "   |    " + session.getGamesPlayed() + " Games");
                //die Hintergrundfarbe verändert sich anhand des Records.
                    //wenn mehr Games gewonnen wurden:
                   if(session.getRecord().contains("W")) {
                	   setStyle("-fx-background-color: green");
                   } //wenn mehr Games verloren wurden:
                   else if(session.getRecord().contains("L")) {
                	   setStyle("-fx-background-color: red");
                   } //wenn die Serie ausgeglichen war:
                   else if(session.getRecord().equals("even")) {
                	   setStyle("-fx-background-color: yellow");
                   }
                }
            }
        });
    }
    	

    
    

}

