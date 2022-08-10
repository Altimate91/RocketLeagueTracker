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

public class LoginDialogController implements Initializable{
	
// ----------- INSTANZVARIABLEN -----------
	

//----------- METHODEN -----------
	
@FXML
private Label headline;
@FXML
private TextField txf_playerID_login;
@FXML
private PasswordField pwf_password_login;
@FXML
private Label lbl_pwCheck;


@FXML
public void login (ActionEvent event) throws IOException, InterruptedException {
	
			
	User user = ManageUser.getUserByPlayerID(txf_playerID_login.getText());
	
	
	if(user == null) {
		new Alert(Alert.AlertType.ERROR, "No User registered under this Player ID!").showAndWait();
		System.out.println("*** Userdata is not availaible in database ***");
	}
	
	if(user.getPassword().equals(pwf_password_login.getText())) {
		MainFX.setMainUser(user);
		System.out.println("*** Login successfull ***");
		new PlayerLoungeController().openPlayerLounge(event);
		
	}
	else if(user.getPassword() != pwf_password_login.getText()) {
		System.out.println("*** ERROR: incorrect Passwort! ***");
		new Alert(Alert.AlertType.ERROR, "Wrong Password!").showAndWait();
	}

}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
