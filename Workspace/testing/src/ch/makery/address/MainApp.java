package ch.makery.address;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import ch.makery.address.model.Book;
import ch.makery.address.model.BookListWrapper;
import ch.makery.address.model.ProductType;
import ch.makery.address.view.BookEditDialogController;
import ch.makery.address.view.OverviewController;
import ch.makery.address.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {



    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    public MainApp()
    {
        bookData.add(new Book("Symfonia C++","Jerzy Grebosz" ,2,ProductType.TEXTBOOK,true));
        bookData.add(new Book("Krzy¿acy","Henryk Sienkiewicz" , 5,ProductType.READING,true));
        bookData.add(new Book("Poradnik ogrodnika", "Jan Kowalski" , 1,ProductType.MANUAL,false));

    }
    public ObservableList<Book> getBookData()
    {
        return bookData;
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("BookApp");

        initRootLayout();

        showBookOverview();
    }


    public void initRootLayout()
    {
        try
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);


            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

/*       File file = getBookFilePath();
        if (file != null)
        {
            loadBookDataFromFile(file);
        } */

    }


    public void showBookOverview()
    {
        try
        {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();


            rootLayout.setCenter(personOverview);


            OverviewController controller = loader.getController();
            controller.setMainApp(this);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }


    public boolean showBookEditDialog(Book book)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);


            BookEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBook(book);


            dialogStage.showAndWait();

            return controller.isOkClicked();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public File getBookFilePath()
    {

    	Preferences prefs = Preferences.userNodeForPackage(MainApp.class);

        String filePath = prefs.get("filePath", null);

        if (filePath != null)
        {
            return new File(filePath);
        }
        else
        {
            return null;
        }
    }

    public void setBookFilePath(File file)
    {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);


        if (file != null)
        {
            prefs.put("filePath", file.getPath());

            primaryStage.setTitle("BookApp - " + file.getName());
        }

        else
        {
            prefs.remove("filePath");

            primaryStage.setTitle("BookApp");
        }
    }


    public void loadBookDataFromFile(File file)
    {
        try
        {
            JAXBContext context = JAXBContext
                    .newInstance(BookListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();


            BookListWrapper wrapper = (BookListWrapper) um.unmarshal(file);

            bookData.clear();
            bookData.addAll(wrapper.getBooks());


            setBookFilePath(file);

        }
        catch (Exception e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }


    public void saveBookDataToFile(File file)
    {
        try
        {
            JAXBContext context = JAXBContext
                    .newInstance(BookListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setEventHandler(new ValidationEventHandler(){

				@Override
				public boolean handleEvent(ValidationEvent arg0) {
					// TODO Auto-generated method stub
					return false;
				}

            });

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            BookListWrapper wrapper = new BookListWrapper();
            wrapper.setbooks(bookData);

            m.marshal(wrapper, file);


            setBookFilePath(file);
        }
        catch (Exception e)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}