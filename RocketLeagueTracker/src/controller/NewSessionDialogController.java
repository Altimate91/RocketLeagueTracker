package controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;

import classes.Game;
import classes.Session;
import classes.User;
import database.DBAccess;
import database.ManageSession;
import database.ManageUser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainFX;

public class NewSessionDialogController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------	

	@FXML
	private Label lbl_player2;
	@FXML
	private CheckBox cb_checkGameLimit;
	@FXML
	private ComboBox<String> cb_gameLimit;
	@FXML
	private TextField txf_indGameLimit;
	@FXML
	private ComboBox<User> cb_teammates;
	
	private ObservableList<User> ol_teammates = FXCollections.observableArrayList();
	
	private String[] numbers = {"5","10","15","20","25","30", "other"};

	
	private User player2;

	
// ----------- METHODEN -----------	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Numbers in ChoiceBox eintragen
		cb_gameLimit.getItems().addAll(numbers);
		//wenn ein GameLimit erwünscht ist wird die Combobox zur Auswahl des Limits sichtbar
		cb_checkGameLimit.setOnAction(e -> {
			if(cb_checkGameLimit.isSelected()) {
				cb_gameLimit.setVisible(true);
			} else {
				cb_gameLimit.setVisible(false);
			}
		});
		
		//wenn ein anderer Wert eingegeben werden soll weil "other" ausgewählt wurde wird Textfeld zur eingabe sichtbar
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
		
		//Teammates in ComboBox eintragen (Alle in der DB verfügbaren Spieler ausser der MainUser)
		ol_teammates.addAll(ManageUser.getTeammateList(MainFX.getMainUser().getIdUser()));
		cb_teammates.setItems(ol_teammates);
					
	}
	
	
	
	
	//Funktion wird bei Drücken des "Add"-Buttons ausgeführt und speichert den ausgewählten Spieler der Combobox als Teammate
	@FXML
	public void addPlayer(ActionEvent event) {
		//Datenbank mit playerID abgleichen
	
		player2 = ManageUser.getUserByPlayerID(cb_teammates.getValue().getPlayer_ID());
		lbl_player2.setText(player2.getPlayer_ID());
		System.out.println("*** Added Player " + player2.getName() + " as Teammate ! ***");

	}
	
	//Wenn ein Spieler in der DB noch nicht vorhanden ist kann dieser durch das Drücken des "Register"-Buttons neu angelegt werden
	@FXML
	public void registerPlayer(ActionEvent event) {	
	new StartController().openNewUserDialog(event);
	}
	
	//Sollte man mit der Auswahl nicht zufrieden sein kann man die Auswahl hier wieder entfernen.
	public void delete_player2(ActionEvent event) {
		System.out.println("*** Dropped " + player2.getName() + " as Teammate ! ***");
		lbl_player2.setText("");
		
	}
	
	
	//Auswahl wird gespeichert und eine neue Session wird mit Parametern erstellt
	@FXML
	public void submit(ActionEvent event) {
		
		int gameLimit = 0;
		
		//SessionID wird durch DB selbständig erzeugt
		Session session = new Session();
		session.setGamesPlayed(0);
		//GameLimit eintragen
		if(cb_checkGameLimit.isSelected()) {
			if(cb_gameLimit.getValue().equals("other")) {
				gameLimit = Integer.parseInt(txf_indGameLimit.getText()); //Wenn Other ausgewählt wurde, wird der Wert aus dem Textfeld genommen
			}
			else {
				gameLimit =  Integer.parseInt(cb_gameLimit.getValue()); //Wenn kein indiv. Limit eingegeben wurde wird vorgegebener Wert aus ComboBox genommen
			}
			System.out.println("*** GameLimit set to " + gameLimit + " Games. ***");
		}
		
		session.setPlayer1(MainFX.getMainUser()); //Spieler 1 wird bekannt gegeben
		session.setPlayer2(ManageUser.getUserByPlayerID(lbl_player2.getText())); //Spieler 2 wird bekannt gegeben
		
		session.setGameLimit(gameLimit); //GameLimit wird bekannt gegeben
		//Wenn dies die erste erstellte Session ist wird eine neue Instanz der ArrayList erstellt damit Session abgespeichert werden kann
		if(MainFX.getSessionList() != null) {
			MainFX.getSessionList().add(session);
		} else {
			MainFX.setSessionList(new ArrayList<Session>());
			MainFX.getSessionList().add(session);
		}
		
		System.out.println("*** New Session opened: Player1: " + MainFX.getMainUser().getName() + " , Teammate: " + session.getPlayer2().getName());
		//Session Objekt an DB übergeben
		 ManageSession.saveSession(session);
	}
		
}
	
