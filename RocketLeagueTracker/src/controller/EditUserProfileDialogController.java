package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.League;
import database.ManageUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import main.MainFX;

public class EditUserProfileDialogController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------	
	
	private String profilepicture;
	
	@FXML
	private ImageView img_profilepic;
	@FXML
	private TextField txf_name;
	@FXML
	private Label lbl_playerID;
	@FXML
	private TextField txf_clan;
	@FXML
	private ComboBox<League> cb_league;
	@FXML
	private PasswordField pwf_password;
	@FXML
	private ProgressBar bar_pwDifficulty;
	@FXML
	private Label lbl_pwDifficulty;
	
	
// ----------- METHODEN -----------	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//Profilbild einspielen
		try {
			img_profilepic.setImage(new Image(new FileInputStream(new File(MainFX.getMainUser().getProfilepicture()))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//Befüllt ChoiceBox mit LeagueEnum-Items
		 cb_league.getItems().setAll(League.values());
		 
		 //momentane Userdaten als PromptText in TextFields setzen
		 txf_name.setPromptText(MainFX.getMainUser().getName());
		 lbl_playerID.setText(MainFX.getMainUser().getPlayer_ID());
		 txf_clan.setPromptText(MainFX.getMainUser().getClan());
		 pwf_password.setPromptText(MainFX.getMainUser().getPassword());
		
	}
	
	@FXML
	public void addProfilePicture(ActionEvent event) throws FileNotFoundException {
		FileChooser fc = new FileChooser();
		fc.setTitle("Choose your Profilepicture");
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*.jpg", "*.png"));
		File file = fc.showOpenDialog(null);
		img_profilepic.setImage(new Image(new FileInputStream(file.getAbsolutePath())));
		profilepicture = file.getAbsolutePath();

	}
	
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
	
	
	public void submitEditedPlayer(ActionEvent event) {
		
		//Felder die vom User mit neuen Werten befüllt wurden sollen im User aktualisiert werden. Wenn Empty -> alter Wert bleibt
		if(!txf_name.getText().isEmpty()) {
		MainFX.getMainUser().setName(txf_name.getText());
		}
		if(!txf_clan.getText().isEmpty()) {
		MainFX.getMainUser().setClan(txf_clan.getText());
		}
		if(cb_league.getValue() != null) {
		MainFX.getMainUser().setLeague(cb_league.getValue().toString());
		}
		if(!pwf_password.getText().isEmpty()) {
		MainFX.getMainUser().setPassword(pwf_password.getText());
		}
		
		//trägt neuen Dateipfad aus Imageview in das User-Objekt ein
		if(profilepicture != null) MainFX.getMainUser().setProfilepicture(profilepicture);
			
		 //User wird in Datenbank angelegt
		 ManageUser.update(MainFX.getMainUser());
		 
		 System.out.println("*** User: " + MainFX.getMainUser().getName() + " wurde bearbeitet ***");
	}

}
