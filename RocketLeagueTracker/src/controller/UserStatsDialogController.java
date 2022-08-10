package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Session;
import classes.User;
import database.ManageSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.MainFX;

public class UserStatsDialogController implements Initializable {
	
	// ----------- INSTANZVARIABLEN -----------	
	
	private Stage dialogStage;
	private User user = MainFX.getMainUser();
	
	@FXML
	private ImageView img_profilepic;
	@FXML
	private Label lbl_userName;
	@FXML
	private Label lbl_topScorer;
	@FXML
	private Label lbl_topWingman;
	@FXML
	private Label lbl_topDefender;
	@FXML
	private Label lbl_goals;
	@FXML
	private Label lbl_saves;
	@FXML
	private Label lbl_assists;
	
	
	
	// ----------- METHODEN -----------	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
			img_profilepic.setImage(new Image(new FileInputStream(new File(user.getProfilepicture()))));
			lbl_userName.setText(MainFX.getMainUser().getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Stats in Labels befüllen
		lbl_goals.setText(Integer.toString(user.getGoals()));
		lbl_assists.setText(Integer.toString(user.getAssists()));
		lbl_saves.setText(Integer.toString(user.getSaves()));
		
		//TopWerte in Labels befüllen
		lbl_topScorer.setText(Integer.toString(this.getUserTopScorer()));
		lbl_topDefender.setText(Integer.toString(this.getUserTopDefender()));
		lbl_topWingman.setText(Integer.toString(this.getUserTopWingman()));
		
	}
	
	public void close(ActionEvent event) {
		//DialogStage schließen
		dialogStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		dialogStage.close();
	}
	
	
	public int getUserTopScorer() {
		int topScorer = 0;
		for(Session aSession : ManageSession.getSessionList(user.getIdUser())) {
			if(aSession.getTopScorer() != null)  {
				if(aSession.getTopScorer().equals(user.getPlayer_ID())) {
					topScorer += 1;
				}
			}
		}
	return topScorer;
	}
	
	public int getUserTopDefender() {
		int topDefender = 0;
		for(Session aSession : ManageSession.getSessionList(user.getIdUser())) {
			if(aSession.getTopDefender() != null)  {
				if(aSession.getTopDefender().equals(user.getPlayer_ID())) {
					topDefender += 1;
				}
			}	
		}
	return topDefender;
	}
	
	public int getUserTopWingman() {
		int topWingman = 0;
		for(Session aSession : ManageSession.getSessionList(user.getIdUser())) {
			if(aSession.getTopWingman() != null)  {
				if(aSession.getTopWingman().equals(user.getPlayer_ID())) {
					topWingman += 1;
				}
			}
		}
	return topWingman;
	}
	
	

}
