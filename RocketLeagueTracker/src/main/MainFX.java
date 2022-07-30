package main;

import java.io.IOException;
import java.util.ArrayList;

import classes.Game;
import classes.Session;
import classes.User;
import controller.StartController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainFX extends Application {
	
	// ----------- INSTANZVARIABLEN -----------
	
	private static Stage stage;
	private static Image icon = new Image("C:\\Users\\andre\\OneDrive\\Dokumente\\GitHub\\GitHubRpositories\\RocketLeagueTracker\\resources\\icon.png");
	private static User mainUser;
	private static ArrayList<Session> sessionList;
	private static ObservableList<Game> olGames = FXCollections.observableArrayList();

	
	private String css = this.getClass().getResource("/view/Stylesheet.css").toExternalForm();
	
	// ------------ GETTER & SETTER -----------	
	
	public static Stage getStage() {
		return stage;
	}
	
	public static Image getIcon() {
		return icon;
	}

	public static void setIcon(Image icon) {
		MainFX.icon = icon;
	}


	public static User getMainUser() {
		return mainUser;
	}

	public static void setMainUser(User user) {
		MainFX.mainUser = user;
	}
	
	public String getCss() {
		return css;
	}
	
	public static ArrayList<Session> getSessionList() {
		return sessionList;
	}
	
	public static void setSessionList(ArrayList<Session> sessionList) {
		MainFX.sessionList = sessionList;
	}

	public static ObservableList<Game> getOlGames() {
		return olGames;
	}

	public static void setOlGames(ObservableList<Game> olGames) {
		MainFX.olGames = olGames;
	}
	
	
	
	// ----------- METHODEN -----------


	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Start.fxml"));
		
		try {
			Parent root = fxmlLoader.load();
			fxmlLoader.setController(new StartController());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(css); // noch leer!
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(icon);
			primaryStage.setTitle("Rocket League Tracker");
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}


}
