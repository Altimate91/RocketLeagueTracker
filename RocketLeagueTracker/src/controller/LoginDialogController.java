package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.User;
import database.ManageUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.MainFX;

public class LoginDialogController{
	
// ----------- INSTANZVARIABLEN -----------
	
	@FXML
	private Label headline;
	@FXML
	private TextField txf_playerID_login;
	@FXML
	private PasswordField pwf_password_login;
	@FXML
	private Label lbl_pwCheck;
	
	private int loginStatus;
	
//----------- GETTER & SETTER -----------
	

	public int getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}


//----------- METHODEN -----------
	
	//Funktion gleicht eingegebene Userdaten mit Daten aus der DB ab. 
	@FXML
	public void login (ActionEvent event) throws IOException, InterruptedException {
		// user Objekt wird erstellt. DB wird auf eingegebene PlayerID durchsucht	
		User user = ManageUser.getUserByPlayerID(txf_playerID_login.getText());
		
		//wenn Userobjekt leer, weil UserID nicht gefunden
		if(user == null) {
			System.out.println("*** Userdata is not availaible in database ***");
			loginStatus = 1;
			return;
		}
		// wenn UserID gefunden aber Passwort gleicht nicht dem Textfeld Password
		else if(!user.getPassword().equals(pwf_password_login.getText())) {
			loginStatus = 2;
			System.out.println("*** ERROR: incorrect Passwort! ***");
		}
		// wenn UserID gefunden und Passwordfeld ist nicht ungültig -> Zutritt zu PlayerLounge
		else {
			MainFX.setMainUser(user);
			System.out.println("*** Login successfull ***");
			new PlayerLoungeController().openPlayerLounge(event);	
		}
	
	}


}
