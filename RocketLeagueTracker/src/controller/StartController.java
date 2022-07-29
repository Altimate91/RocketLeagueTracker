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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;
import main.MainFX;

public class StartController implements Initializable {
	
// ----------- INSTANZVARIABLEN -----------
		
	private Parent root;
	private String css = this.getClass().getResource("/view/Stylesheet.css").toExternalForm();
	
	private User user;
	
	
//----------- METHODEN -----------

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	@FXML
	public void openLoginDialog (ActionEvent event) {
		
		try {	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginDialog.fxml"));
			root = loader.load();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.getDialogPane().setContent(root);
			dialog.getDialogPane().getStylesheets().add(css);
			dialog.setTitle("Create New Member");
			System.out.println("--- Opened Login Dialog ---");
			
			
			ButtonType btnSubmit = new ButtonType("Submit", ButtonData.OK_DONE);
			ButtonType btnClose = new ButtonType("Close", ButtonData.CANCEL_CLOSE);
			
			dialog.getDialogPane().getButtonTypes().addAll(btnSubmit, btnClose);
			
			Optional<ButtonType> response = dialog.showAndWait();
			if(response.isPresent() && response.get().getButtonData() == ButtonData.OK_DONE) {
				
				LoginDialogController loginController = loader.getController();
				try {
					event.consume();
					loginController.login(event);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			

		
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}

	@FXML
	public void openNewUserDialog (ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/NewUserDialog.fxml"));
			root = loader.load();
			
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.getDialogPane().setContent(root);
			dialog.getDialogPane().getStylesheets().add(css);
			dialog.setTitle("User Login");
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
