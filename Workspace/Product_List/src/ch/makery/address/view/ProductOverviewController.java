package ch.makery.address.view;

import javafx.fxml.FXML;
import ch.makery.address.model.Product;
import ch.makery.address.MainApp;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ProductOverviewController {

	 	@FXML
	    private TableView<Product> productTable;
	    @FXML
	    private TableColumn<Product, String> nameColumn;
	    @FXML
	    private TableColumn<Product, Integer> quanityColumn;
	    
	    @FXML
	    private Label nameLabel;
	    @FXML
	    private Label quanityLabel;
	    @FXML
	    private Label typeLabel;
	    @FXML
	    private Label suppliedLabel;
	    
	    
	    private MainApp mainApp;
	
	    public ProductOverviewController() {
	    }
	    
	    @FXML
	    private void initialize() {
	    	// Initialize the person table with the two columns.
	        nameColumn.setCellValueFactory(
	        		cellData -> cellData.getValue().nameProperty());
	        quanityColumn.setCellValueFactory(
	        		cellData -> cellData.getValue().quanityProperty().asObject());
	        
	        // Clear person details.
	        showProductDetails(null);

	        // Listen for selection changes and show the person details when changed.
			productTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showProductDetails(newValue));
	    }
	    
	    
	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        productTable.setItems(mainApp.getProductData());
	    }
	    
	    /**
	     * Fills all text fields to show details about the person.
	     * If the specified person is null, all text fields are cleared.
	     * 
	     * @param product the person or null
	     */
	    private void showProductDetails(Product product) {
	    	if (product != null) {
	    		// Fill the labels with info from the person object.
	    		nameLabel.setText(product.getName());
	    		quanityLabel.setText((String.valueOf(product.getQuanity())));
	    		typeLabel.setText(product.getType().toString());
	    		suppliedLabel.setText((String.valueOf(product.getSuppliedString())));
	    	
	    	} else {
	    		// Person is null, remove all the text.
	    		nameLabel.setText("");
	    		quanityLabel.setText("");
	    		typeLabel.setText("");
	    		suppliedLabel.setText("");
	    	}
	    }
	    
	    /**
	     * Called when the user clicks on the delete button.
	     */
	    @FXML
	    private void handleDeleteProduct() {
	        int selectedIndex = productTable.getSelectionModel().getSelectedIndex();
	        if (selectedIndex >= 0) {
	            productTable.getItems().remove(selectedIndex);
	        } else {
	            // Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Product Selected");
	            alert.setContentText("Please select a product in the table.");

	            alert.showAndWait();
	        }
	    }
	    
	    /**
	     * Called when the user clicks the new button. Opens a dialog to edit
	     * details for a new person.
	     */
	    @FXML
	    private void handleNewProduct() {
	        Product tempProduct = new Product();
	        boolean okClicked = mainApp.showProductEditDialog(tempProduct);
	        if (okClicked) {
	            mainApp.getProductData().add(tempProduct);
	        }
	    }
	    
	    /**
	     * Called when the user clicks the edit button. Opens a dialog to edit
	     * details for the selected person.
	     */
	    @FXML
	    private void handleEditProduct() {
	        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
	        if (selectedProduct != null) {
	            boolean okClicked = mainApp.showProductEditDialog(selectedProduct);
	            if (okClicked) {
	                showProductDetails(selectedProduct);
	            }

	        } else {
	            // Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Product Selected");
	            alert.setContentText("Please select a product in the table.");

	            alert.showAndWait();
	        }
	    }
	
}
