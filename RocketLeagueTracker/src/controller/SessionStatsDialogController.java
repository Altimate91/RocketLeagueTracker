package controller;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.MainFX;

public class SessionStatsDialogController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------	
	
	private Stage dialogStage;	
	
	@FXML
	private Label lbl_player1_name;
	@FXML
	private Label lbl_player2_name;
	@FXML
	private Label lbl_goals_p1;
	@FXML
	private Label lbl_assists_p1;
	@FXML
	private Label lbl_saves_p1;
	@FXML
	private Label lbl_mvp_p1;
	@FXML
	private Label lbl_goals_p2;
	@FXML
	private Label lbl_assists_p2;
	@FXML
	private Label lbl_saves_p2;
	@FXML
	private Label lbl_mvp_p2;
	@FXML
	private Label lbl_topScorer;
	@FXML
	private Label lbl_topDefender;
	@FXML
	private Label lbl_topWingman;
	

	
// ----------- METHODEN -----------	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Session currentSession = null;
		
		if(MainFX.getSessionList() != null) {
			//aktuelle Session wird ausgelesen		
			currentSession = MainFX.getSessionList().get(MainFX.getSessionList().size() -1);
		}
		
		lbl_player1_name.setText(currentSession.getPlayer1().getName());
		lbl_player2_name.setText(currentSession.getPlayer2().getName());
		
		//Werte eintragen
		lbl_goals_p1.setText(Integer.toString(currentSession.goalsByPlayer1()));
		lbl_goals_p2.setText(Integer.toString(currentSession.goalsByPlayer2()));
		
		lbl_saves_p1.setText(Integer.toString(currentSession.savesByPlayer1()));
		lbl_saves_p2.setText(Integer.toString(currentSession.savesByPlayer2()));
		
		lbl_assists_p1.setText(Integer.toString(currentSession.assistsByPlayer1()));
		lbl_assists_p2.setText(Integer.toString(currentSession.assistsByPlayer2()));
		
		//topWerte eintragen
		lbl_topScorer.setText(currentSession.getTopScorer());
		lbl_topDefender.setText(currentSession.getTopDefender());
		lbl_topWingman.setText(currentSession.getTopWingman());
		
	}
	
	
	public void close (ActionEvent event) {
		dialogStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		dialogStage.close();
	}
		

}
