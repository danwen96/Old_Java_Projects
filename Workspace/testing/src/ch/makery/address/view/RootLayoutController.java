package ch.makery.address.view;

import java.io.File;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {

    
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleNew()
    {
        mainApp.getBookData().clear();
        mainApp.setBookFilePath(null);
    }


    @FXML
    private void handleOpen()
    {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter( "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null)
        {
            mainApp.loadBookDataFromFile(file);
        }
    }


    @FXML
    private void handleSave()
    {
        File bookFile = mainApp.getBookFilePath();
        if (bookFile != null)
        {
            mainApp.saveBookDataToFile(bookFile);
        }
        
        else
        {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs()
    {
        FileChooser fileChooser = new FileChooser();

        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null)
        { 
            if (!file.getPath().endsWith(".xml"))
            {
                file = new File(file.getPath() + ".xml");
            } 
            mainApp.saveBookDataToFile(file);
        }
    }

    @FXML
    private void handleAbout()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("BookApp");
        alert.setHeaderText("About");
        

        alert.showAndWait();
    }

    @FXML
    private void handleExit()
    {
        System.exit(0);
    }
}