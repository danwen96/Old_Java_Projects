package ch.makery.address.view;

import ch.makery.address.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ProductEditDialogController {
	
		@FXML
	    private TextField nameField;
	    @FXML
	    private TextField quanityField;
	    @FXML
	    private ComboBox<Product.ProductType> typeField;
	    
	    @FXML
	    private CheckBox suppliedBox;
	    
	    
	    private Stage dialogStage;
	    private Product product;
	   // private ObservableList<String> options = FXCollections.observableArrayList(Product.ProductType.);
	    
	    private boolean okClicked = false;
	
	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }
	    
	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }
	    
	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param product
	     */
	    public void setProduct(Product product) {
	        this.product = product;

	        nameField.setText(product.getName());
	        quanityField.setText(Integer.toString(product.getQuanity()));
	        typeField.getItems().setAll(Product.ProductType.values());
	        typeField.setValue(product.getType());
	        suppliedBox.setSelected(product.getSupplied());
	       
	    }
	    
	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            product.setName(nameField.getText());
	            product.setQuanity(Integer.parseInt(quanityField.getText()));
	            product.setType(typeField.getValue());
	            product.setSupplied(suppliedBox.isSelected());

	            okClicked = true;
	            dialogStage.close();
	        }
	    }
	    
	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }
	    
	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (nameField.getText() == null || nameField.getText().length() == 0) {
	            errorMessage += "No valid product name!\n"; 
	        }   

	        if (quanityField.getText() == null || quanityField.getText().length() == 0) {
	            errorMessage += "No valid quanity code!\n"; 
	        } else {
	            // try to parse the postal code into an int.
	            try {
	                Integer.parseInt(quanityField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "No valid quanity number (must be an integer)!\n"; 
	            }
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }



}
