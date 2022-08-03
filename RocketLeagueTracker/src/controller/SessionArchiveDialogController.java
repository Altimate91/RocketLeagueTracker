package controller;

import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.MainFX;

public class SessionArchiveDialogController implements Initializable {
	
	
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
    private Label lbl_sessionMVP;

    @FXML
    private ListView<Session> lv_sessionArchive;

    @FXML
    private TableView<PlayerStatistic> tbl_table;
    
    private ObservableList<PlayerStatistic> ol_playerStatistic = FXCollections.observableArrayList();
    
    Session currentSession;

// ----------- METHODEN -----------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    	//SessionList aus DB in die List View Laden
    	
    	currentSession = ManageSession.getCurrentSession();
    	
    	List<Session> sessionList = ManageSession.getSessionList();
    	
    	
    	//List View mit gespielten Sessions befüllen
    	lv_sessionArchive.getItems().addAll(sessionList);
    	
    	//Ausgewählte Session gibt dazugehörige Stats der Session aus
    	lv_sessionArchive.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Session>() {

			@Override
			public void changed(ObservableValue<? extends Session> arg0, Session arg1, Session arg2) {
				if(arg2 != null) {
					
					//List View
					
					lbl_gamesPlayed.setText(Integer.toString(arg2.getGamesPlayed()));
					lbl_wins.setText(Integer.toString(arg2.getWins()));
					lbl_defeats.setText(arg2.getRecord());
					lbl_goals.setText(Integer.toString(arg2.getGoalsScored()));
					lbl_saves.setText(Integer.toString(arg2.getSaves()));
					lbl_assists.setText(Integer.toString(arg2.getAssists()));
					lbl_received.setText(Integer.toString(arg2.getGoalsReceived()));
					
					// Table View
					
					ol_playerStatistic = (ObservableList<PlayerStatistic>) currentSession.getPlayerStatisticList();
					
					col_player.setCellValueFactory(new PropertyValueFactory<PlayerStatistic, String>("playername"));
					col_goal.setCellValueFactory(new PropertyValueFactory<PlayerStatistic, Integer>("goals"));
					col_saves.setCellValueFactory(new PropertyValueFactory<PlayerStatistic, Integer>("saves"));
					col_goal.setCellValueFactory(new PropertyValueFactory<PlayerStatistic, Integer>("assists"));
					tbl_table.setItems(ol_playerStatistic);
					
					
				}
				
			}
    		
    	});
    	
    	
    	
    	
    	
    	
    }
    
    
    
    
    
    
    

}

