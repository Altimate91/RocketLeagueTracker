package controller;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Session;
import database.ManageSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.MainFX;

public class SessionStatsDialogController implements Initializable {
	
	/*
	 * ?bersichtsfenster dient zur Anzeige der laufenden Statistik einer Session.
	 * Kann nur ge?ffnet werden wenn eine Session offen ist.
	 */
	
// ----------- INSTANZVARIABLEN -----------	

	private Session currentSession;
	
	@FXML
	private Label headline;
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
	private Label lbl_sessionMVP;
	@FXML
	private Label lbl_topScorer;
	@FXML
	private Label lbl_topDefender;
	@FXML
	private Label lbl_topWingman;
	
	

	
// ----------- METHODEN -----------	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(MainFX.getCurrentSession() != null) {
			//aktuelle Session wird ausgelesen
			currentSession = ManageSession.getCurrentSession();
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
		
		lbl_mvp_p1.setText(Integer.toString(currentSession.mvpByPlayer1()));
		lbl_mvp_p2.setText(Integer.toString(currentSession.mvpByPlayer2()));
		
		//topWerte eintragen
		if(currentSession.sessionTopScorer() != null) lbl_topScorer.setText(currentSession.sessionTopScorer().getName()); else lbl_topScorer.setText("equal");
		if(currentSession.sessionTopDefender() != null) lbl_topDefender.setText(currentSession.sessionTopDefender().getName()); else lbl_topDefender.setText("equal");
		if(currentSession.sessionTopWingman() != null) lbl_topWingman.setText(currentSession.sessionTopWingman().getName()); else lbl_topWingman.setText("equal");
		if(currentSession.getSessionMVP() != null) lbl_sessionMVP.setText(currentSession.sessionMVP().getName()); else lbl_sessionMVP.setText("no SessionMVP");
		
	}
	

}
