package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import classes.Session;
import classes.User;
import database.DBAccess;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainFX;

public class NewSessionDialogController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------	

	@FXML
	private TextField txf_playerID;
	@FXML
	private CheckBox cb_checkGameLimit;
	@FXML
	private ComboBox<String> cb_gameLimit;
	@FXML
	private TextField txf_indGameLimit;
	
	private String[] numbers = {"5","10","15","20","25","30", "other"};
	
	private Session session;
	
	private User player2;

	
// ----------- METHODEN -----------	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Numbers in ChoiceBox eintragen
		cb_gameLimit.getItems().addAll(numbers);
		
		cb_checkGameLimit.setOnAction(e -> {
			if(cb_checkGameLimit.isSelected()) {
				cb_gameLimit.setVisible(true);
			} else {
				cb_gameLimit.setVisible(false);
			}
		});
		
		cb_gameLimit.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(arg2.equals("other")) {
					txf_indGameLimit.setVisible(true);
				}
				else {
					txf_indGameLimit.setVisible(false);
				}
			}
		});
					
	}
	
	
	@FXML
	public void addPlayer(ActionEvent event) {
		
		//abgleich mit txf_playerID in Datenbank ob Player vorhanden und wenn ja dann in Session PlayerList eintragen
		
		
		
		
	}
	
	@FXML
	public void registerPlayer(ActionEvent event) {
		
	FXMLLoader loader = new FXMLLoader();
		
	new StartController().openNewUserDialog(event);
	}
	
	
	
	@FXML
	public void submit(ActionEvent event) {
		
		int gameLimit = 0;
		
		this.session = new Session();
		session.setGamesPlayed(0);
		//SessionID soll durch DB eigens erzeugt werden
		session.setDate(LocalDateTime.now());
		//GameLimit eintragen
		if(cb_checkGameLimit.isSelected()) {
			if(cb_gameLimit.getValue().equals("other")) {
				gameLimit = Integer.parseInt(txf_indGameLimit.getText());
			}
			else {
				gameLimit =  Integer.parseInt(cb_gameLimit.getValue());
			}
			System.out.println("*** GameLimit set to " + gameLimit + " Games. ***");
		}
		
		session.setGameLimit(gameLimit);
		
		if(MainFX.getSessionList() != null) {
		MainFX.getSessionList().add(session);
		} else {
			MainFX.setSessionList(new ArrayList<Session> ());
			MainFX.getSessionList().add(session);
		}
		//GameLimit an PlayerLounge übergeben
		
		// DBAccess.save(session); 
		
		
		
	}
		
}
	
