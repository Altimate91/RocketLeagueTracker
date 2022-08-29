package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import classes.User;
import database.DBAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.MainFX;

public class StartController {
	
	/*
	 * Controller der Startseite.
	 * User-Login oder User anlegen kann ausgewählt werden.
	 */
	
// ----------- INSTANZVARIABLEN -----------
		
	private Parent root;
	private String css = this.getClass().getResource("/view/Stylesheet.css").toExternalForm();
	
	@FXML
	private Label lbl_loginStatus;
	
//----------- METHODEN -----------

	//Login Dialog wird aufgerufen nach drücken des Login-Buttons
	@FXML
	public void openLoginDialog (ActionEvent event) {
		
		try {	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginDialog.fxml"));
			root = loader.load();
			LoginDialogController loginController = loader.getController();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.getDialogPane().setContent(root);
			dialog.getDialogPane().getStylesheets().add(css);
			dialog.setTitle("User Login");
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(MainFX.getIcon()); //Icon für Dialog-Window einfügen
			
			System.out.println("--- Opened Login Dialog ---");
			
			
			ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
			ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			
			dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
			
			Optional<ButtonType> response = dialog.showAndWait();
			if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
				
				
				try {
					loginController.login(event); //Login-Methode aus loginController wird aufgerufen um Eingabe mit DB abzugleichen
				
					switch(loginController.getLoginStatus()) {
					case 1 : lbl_loginStatus.setText("User not found!");  lbl_loginStatus.setTextFill(Color.RED); lbl_loginStatus.setVisible(true); //UserDaten nicht vorhanden
					break;
					case 2 : lbl_loginStatus.setText("Password invalid!");  lbl_loginStatus.setTextFill(Color.RED); lbl_loginStatus.setVisible(true); //User Passwort nicht richtig
					break;
					}
		
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			

		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

	//Funktion wird aufgerufen bei Register-Button um neuen User anzulegen
	@FXML
	public void openNewUserDialog (ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewUserDialog.fxml"));
			root = loader.load();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.getDialogPane().setContent(root);
			dialog.getDialogPane().getStylesheets().add(css);
			dialog.setTitle("User Login");
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons().add(MainFX.getIcon()); //Icon für Dialog-Window einfügen
			
			System.out.println("--- Opened New Member Dialog ---");
			
			ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
			ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			
			dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
			
			Optional<ButtonType> response = dialog.showAndWait();
			if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
				
				NewUserDialogController userController = loader.getController();
				userController.submitNewPlayer(event);
			}			
					
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
