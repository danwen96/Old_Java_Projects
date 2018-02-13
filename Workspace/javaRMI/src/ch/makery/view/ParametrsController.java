package ch.makery.view;

import java.io.IOException;

import ch.makery.MainAppRMI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParametrsController {
	@FXML
	private TextField textField;
	@FXML
	private TextField textFieldName;
	
	private ObservableList<Canvas> canvases = FXCollections.observableArrayList();
	
	private String serverName = "rmi://localhost:1099/TestServer";
	
	private Stage primaryStage;
	
	private MainAppRMI mainApp;
	
	@FXML
	private void initialize()
	{
		//primaryStage = mainApp.getPrimaryStage();
		textField.setText(serverName);
		//primaryStage = mainApp.getPrimaryStage();
		textFieldName.setText("TestServer");
	}
	
	@FXML
	private void handleServerMode()
	{
		/*boolean okClicked = mainApp.showServerBoard();
		if(okClicked)
			System.out.println("kliknieto endGame");*/
		
		try {
            // Load the fxml file and create a new stage for the popup dialog.
			serverName = textFieldName.getText();
			
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppRMI.class.getResource("view/GameBoard.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            GameBoardController controller = new GameBoardController(serverName);
           // controller.setServerName(serverName);
            loader.setController(controller);
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Server");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
           
            
            //GameBoardController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            
            dialogStage.showAndWait();
            
            //return controller.isEndGameClicked();
        } catch (IOException e) {
            e.printStackTrace();
            //return false;
        }
	}
	
	@FXML
	private void handleClientMode()
	{
		try {
            // Load the fxml file and create a new stage for the popup dialog.
			
			serverName = textField.getText();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppRMI.class.getResource("view/GameBoardClient.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            GameBoardClientController controller = new GameBoardClientController(serverName);
            loader.setController(controller);
            
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Client");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            
            //GameBoardController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            
            dialogStage.showAndWait();
            
            //return controller.isEndGameClicked();
        } catch (IOException e) {
            e.printStackTrace();
            //return false;
        }
		
	}
	
}
