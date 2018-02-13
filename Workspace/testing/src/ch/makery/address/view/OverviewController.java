package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Book;

public class OverviewController
{
		@FXML
	    private TableView<Book> booksTable;
		@FXML
	    private TableColumn<Book, String> firstColumn;



		@FXML
	    private Label titleLabel;
	    @FXML
	    private Label authortLabel;
	    @FXML
	    private Label typeLabel;
	    @FXML
	    private Label amountLabel;
	    @FXML
	    private Label availableLabel;

	    @FXML
	    // Reference to the main application.
	    private MainApp mainApp;

	    public OverviewController()
	    {
	    }
	    @FXML
	    private void initialize()
	    {
	        firstColumn.setCellValueFactory(cellData -> cellData.getValue().getName());

	        // Clear person details.
	        showBookDetails(null);

	        // Listen for selection changes and show the person details when changed.
	        booksTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showBookDetails(newValue));
	    }

	    public void setMainApp(MainApp mainApp)
	    {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        booksTable.setItems(mainApp.getBookData());

	    }

	    private void showBookDetails(Book book) {
	        if (book != null) {
	        	titleLabel.setText((book.getTitle()));;
	            authortLabel.setText(book.getStringAuthor());
	            typeLabel.setText(String.valueOf(book.getProductType()));
	            amountLabel.setText(String.valueOf(book.getIntAmount()));
	            availableLabel.setText(String.valueOf(book.getavailable()));


	        } else {

	        	titleLabel.setText("");;
	            authortLabel.setText("");
	            typeLabel.setText("");
	            amountLabel.setText("");
	            availableLabel.setText("");

	        }

	    }
	     @FXML
	     private void deleteBook()
	        {
	          int selectedIndex = booksTable.getSelectionModel().getSelectedIndex();
	          if(selectedIndex >=0)
	          booksTable.getItems().remove(selectedIndex);

	          else
	          {
	        	  Alert alert = new Alert(AlertType.WARNING);
	              alert.initOwner(mainApp.getPrimaryStage());
	              alert.setTitle("No Selection");
	              alert.setHeaderText("No Person Selected");
	              alert.setContentText("Please select a person in the table.");

	              alert.showAndWait();
	          }
	        }


	     @FXML
	     private void handleNewBook() {
	         Book tempBook = new Book();
	         boolean okClicked = mainApp.showBookEditDialog(tempBook);
	         if (okClicked) {
	             mainApp.getBookData().add(tempBook);
	         }
	     }


	     @FXML
	     private void handleEditPerson() {
	         Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
	         if (selectedBook != null)
	         {
	             boolean okClicked = mainApp.showBookEditDialog(selectedBook);
	             if (okClicked)
	             {
	                 showBookDetails(selectedBook);
	             }

	         }

	         else
	         {
	             Alert alert = new Alert(AlertType.WARNING);
	             alert.initOwner(mainApp.getPrimaryStage());
	             alert.setTitle("No Selection");
	             alert.setHeaderText("No Person Selected");
	             alert.setContentText("Please select a person in the table.");

	             alert.showAndWait();
	         }
	     }
}
