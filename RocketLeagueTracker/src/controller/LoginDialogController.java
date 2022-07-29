package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.MainFX;

public class LoginDialogController implements Initializable{
	
// ----------- INSTANZVARIABLEN -----------
	
	private Stage dialogStage;
	private String css = this.getClass().getResource("/view/Stylesheet.css").toExternalForm();

	


//----------- METHODEN -----------
	

@FXML
private TextField txf_playerID_login;
@FXML
private PasswordField pwf_password_login;
@FXML
private Label lbl_pwCheck;


@FXML
public void login (ActionEvent event) throws IOException, InterruptedException {
	
	

	
	
			
	if(MainFX.getUser() != null && MainFX.getUser().getPlayer_ID().equals(txf_playerID_login.getText())) {
		
		if(MainFX.getUser().getPassword().equals(pwf_password_login.getText())) {
			new PlayerLoungeController().openPlayerLounge(event);
			System.out.println("*** Login successfull ***");
			
		} else {
			lbl_pwCheck.setText("incorrect Password");
			lbl_pwCheck.setTextFill(Color.RED);
			System.out.println("*** ! incorrect Passwort ! ***");
		}
			
	} else {
		new Alert(Alert.AlertType.ERROR, "User nicht vorhanden!").showAndWait();
		System.out.println("Userdata is not availaible in database.");
	}
}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
