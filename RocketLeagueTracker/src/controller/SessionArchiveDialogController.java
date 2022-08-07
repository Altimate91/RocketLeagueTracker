package controller;

import java.net.URL;
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

public class SessionArchiveDialogController extends ListCell<Session> implements Initializable  {
	
	
// ----------- INSTANZVARIABLEN -----------
	
	@FXML
	private TableColumn<PlayerStatistic, String> col_player;

	@FXML
	private TableColumn<PlayerStatistic, Integer> col_goal;
	
	@FXML
	private TableColumn<PlayerStatistic, Integer> col_saves;
	
    @FXML
    private TableColumn<PlayerStatistic, Integer> col_assists;

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

    @FXML
    private TableView<PlayerStatistic> tbl_table;
    
    private ObservableList<PlayerStatistic> ol_playerStatistic = FXCollections.observableArrayList();

// ----------- METHODEN -----------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	//SessionList aus DB in die List View Laden
    
    	ArrayList<Session> sessionList = (ArrayList<Session>) ManageSession.getSessionList(MainFX.getMainUser().getIdUser());
    	//Objekt mit ToString auf ListView ausgeben

    	System.out.println("List Size: " + Integer.toString(sessionList.size()));
    	System.out.println(sessionList.get(0));
    	//List View mit gespielten Sessions befüllen
    		lv_sessionArchive.getItems().addAll(sessionList);
    	
    	
    	//Ausgewählte Session gibt dazugehörige Stats der Session aus
    	lv_sessionArchive.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Session>() {

			@Override
			public void changed(ObservableValue<? extends Session> arg0, Session arg1, Session arg2) {
				if(arg2 != null) {
					
					Session selectedSession = ManageSession.loadSession(arg2.getIdSession());
					
					//List View
					
					lbl_gamesPlayed.setText(Integer.toString(selectedSession.getGamesPlayed()));
					lbl_wins.setText(Integer.toString(selectedSession.getWins()));
					lbl_defeats.setText(selectedSession.getRecord());
					lbl_goals.setText(Integer.toString(selectedSession.getGoalsScored()));
					lbl_saves.setText(Integer.toString(selectedSession.getSaves()));
					lbl_assists.setText(Integer.toString(selectedSession.getAssists()));
					lbl_received.setText(Integer.toString(selectedSession.getGoalsReceived()));
			
				}	
			}	
    	});	
    }
    
    
    
    @Override
    protected void updateItem(Session session, boolean empty) {
        super.updateItem(session, empty);

        if(empty || session == null) {
            setText(null);
        }
        else {
        	setText(session.getDate() + " / Record: " + session.getRecord() +" / Games: " + Integer.toString(session.getGamesPlayed()));
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
    
    
    
    
    
    
    

}

