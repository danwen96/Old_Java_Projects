package ch.makery.address;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//import java.io.File;
import java.io.IOException;

//import ch.makery.address.model.*;
//import ch.makery.address.view.DrawingFunctionController;


public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("MainApp");
		
		initRootLayout();
		showDrawingFunction();
	}
	
	public MainApp(){}
	
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	 public void showDrawingFunction() {
	        try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/DrawingFunction.fxml"));
	            AnchorPane drawingFunction = (AnchorPane) loader.load();

	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(drawingFunction);

	            // Give the controller access to the main app.
	           // DrawingFunctionController controller = loader.getController();
	            //controller.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	 public Stage getPrimaryStage() {
	        return primaryStage;
	    }

	public static void main(String[] args) {
		launch(args);
			
	}
}
