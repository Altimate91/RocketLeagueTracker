package controller;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Game;
import classes.Session;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SessionArchiveDialogController implements Initializable {
	
	
// ----------- INSTANZVARIABLEN -----------
	

    @FXML
    private TableColumn<Game, Integer> col_assists;

    @FXML
    private TableColumn<Game, Integer> col_goal;

    @FXML
    private TableColumn<Game, String> col_player;

    @FXML
    private TableColumn<Game, Integer> col_saves;

    @FXML
    private Label lbl_assists;

    @FXML
    private Label lbl_defeats;

    @FXML
    private Label lbl_gamesPlayed;

    @FXML
    private Label lbl_goals;

    @FXML
    private Label lbl_received;

    @FXML
    private Label lbl_record;

    @FXML
    private Label lbl_saves;

    @FXML
    private Label lbl_wins;

    @FXML
    private ListView<?> lv_sessionArchive;

    @FXML
    private TableView<Session> tbl_table;


// ----------- INSTANZVARIABLEN -----------/

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	// TODO Auto-generated method stub
    	
    }

}

