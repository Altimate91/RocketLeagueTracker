package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.Session;
import classes.User;
import database.ManageSession;
import database.ManageUser;
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
	
	/*
	 * Zeigt eine Gesamtübersicht der Statistiken des angemeldeten Users.
	 */
	
	// ----------- INSTANZVARIABLEN -----------	
	
	private Stage dialogStage;
	private User user;
	
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
		user = ManageUser.getUserByPlayerID(MainFX.getMainUser().getPlayer_ID());

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
		lbl_topScorer.setText(Integer.toString(user.getTopScorer()));
		lbl_topDefender.setText(Integer.toString(user.getTopDefender()));
		lbl_topWingman.setText(Integer.toString(user.getTopWingman()));
		
	}
	
	public void close(ActionEvent event) {
		//DialogStage schließen
		dialogStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		dialogStage.close();
	}


}
