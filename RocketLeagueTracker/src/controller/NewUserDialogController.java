package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.League;
import classes.Session;
import classes.User;
import database.DBAccess;
import database.ManageUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.MainFX;

public class NewUserDialogController implements Initializable{
	
	/*
	 * Neuer User wird angelegt und in DB abgespeichert.
	 */
	
// ----------- INSTANZVARIABLEN -----------
	
	@FXML
	private PasswordField pwf_password;
	@FXML
	private ProgressBar bar_pwDifficulty;
	@FXML
	private Label lbl_pwDifficulty;
	@FXML
	private ComboBox<League> cb_league;
	@FXML
	private ImageView profilepic_imageview;
	@FXML
	private TextField txf_name;
	@FXML
	private TextField txf_playerID;
	@FXML
	private TextField txf_clan;
	
	private String profilepicture;
	private User user;
	
	

//----------- METHODEN -----------
	

	//Initialize-Methode wird sofort bei Programmstart ausgeführt
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Befüllt ChoiceBox mit Enum-Items
		 cb_league.getItems().setAll(League.values());
	}
	
	
	//Funktion zeigt dem User den Sicherheitsgrad des eingegeben Passworts an -> je länger desto sicherer
	//Ein eingefärbter Balken wird bei Eingabe rot,gelb,grün mit Text verändert
	@FXML
	public void increaseProgress (KeyEvent event) {	
		
		if (pwf_password.getLength() < 5) {
			bar_pwDifficulty.setProgress(0.2);
			bar_pwDifficulty.setStyle("-fx-accent: red;");
			lbl_pwDifficulty.setText("weak");
			lbl_pwDifficulty.setStyle("-fx-text-fill: red;");
		}
		else if (pwf_password.getLength() >= 5 && pwf_password.getLength() < 10) {
			bar_pwDifficulty.setProgress(0.5);
			bar_pwDifficulty.setStyle("-fx-accent: yellow;");
			lbl_pwDifficulty.setText("medium");
			lbl_pwDifficulty.setStyle("-fx-text-fill: yellow;");
		}
		else if (pwf_password.getLength() >= 10) {
			bar_pwDifficulty.setProgress(1);
			bar_pwDifficulty.setStyle("-fx-accent: green;");
			lbl_pwDifficulty.setText("strong");
			lbl_pwDifficulty.setStyle("-fx-text-fill: green;");
		}
	}
	
	//Funktion wird bei Drücken des "Add Buttons" ausgeführt und öffnet den Explorer zum auswählen eines Bildpfads
	@FXML
	public void addProfilePicture(ActionEvent event) throws FileNotFoundException {
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose your Profilepicture");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*.jpg", "*.png"));
		File file = fc.showOpenDialog(null);
		profilepic_imageview.setImage(new Image(new FileInputStream(file.getAbsolutePath())));
		profilepicture = file.getAbsolutePath();
	
	}
	
	
	//Wird beim betätigen des Submit Buttons ausgeführt und speichert die eingegeben Textfelder in der DB
	public void submitNewPlayer(ActionEvent event) throws FileNotFoundException {
		
		user =	new User(txf_playerID.getText(), txf_name.getText(), txf_clan.getText(), cb_league.getValue().toString(), pwf_password.getText());	
		
		//trägt Dateipfad aus Imageview in das User-Objekt ein wenn ein Bild gewählt wurde -> ansonsten lt. Konstruktor StandardProfilepic
		if(profilepicture != null) {
		user.setProfilepicture(profilepicture);
		}
		else{
			user.setProfilepicture(".\\resources\\standardProfilepic.png");
		}
		
	
		
		
		//neuer User wird an Datebank übergeben
		DBAccess.save(user);
	
		System.out.println("*** neuer User: " +user.getName() + " wurde angelegt ***");
		System.out.println(user.toString());
	}

}
