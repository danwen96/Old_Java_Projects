package ch.makery.address.view;

import ch.makery.address.model.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import javafx.scene.paint.Color;
import javafx.scene.control.TextField;

import java.awt.Color;
import java.awt.image.BufferedImage;


import ch.makery.address.MainApp;
//import ch.makery.address.model.Person;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

public class DrawingFunctionController {
	
	@FXML
	private Canvas canvas;
	@FXML
	private Label integral;
	@FXML
	private TextField repetition;
	@FXML
	private ProgressBar progressBar;
	
	double valOfIntegral;
	
	private DrawerTask task;
	
	@FXML
	private void handleRunBtnAction(){
	GraphicsContext gc = canvas.getGraphicsContext2D();
	task = new DrawerTask(gc,Integer.parseInt(repetition.getText()));
	task.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
		@Override
		public void handle (WorkerStateEvent event){
			 valOfIntegral = (double) task.getValue();
			 integral.setText((String.valueOf(valOfIntegral)));
		}	
	});
	
	progressBar.progressProperty().bind(task.progressProperty());
	new Thread(task).start();
	}
	
	@FXML
	private void handleStopBtnAction(){
	task.cancel();
	}

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public DrawingFunctionController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
       integral.setText("Obliczam...");
       repetition.setText("1000001");
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
       // personTable.setItems(mainApp.getPersonData());
    }
    
}