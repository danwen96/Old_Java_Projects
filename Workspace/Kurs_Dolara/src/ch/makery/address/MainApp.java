package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {


    private Stage primaryStage;
    //private BorderPane rootLayout;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Puzzle");

        //initRootLayout();

        showPanel();
	}
	
	public void showPanel() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Panel.fxml"));
            AnchorPane board = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            Scene scene = new Scene(board);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //rootLayout.setCenter(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		launch(args);
	}
}
