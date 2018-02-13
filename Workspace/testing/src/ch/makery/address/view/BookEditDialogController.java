package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Book;
import ch.makery.address.model.ProductType;


public class BookEditDialogController {

    @FXML
    private TextField titleField;   
    @FXML
    private TextField authorField;
    @FXML
    private ComboBox typeField;
    @FXML
    private TextField amountField;
    @FXML
    private CheckBox isAvailable;
    
    

    private Stage dialogStage;
    private Book book;
    private boolean okClicked = false;

    @FXML
    private void initialize()
    {
    	typeField.getItems().setAll(ProductType.values());
    }

    public void setDialogStage(Stage dialogStage)
    {
        this.dialogStage = dialogStage;
    }


    public void setBook(Book book) {
        this.book = book;

        titleField.setText(String.valueOf(book.getTitle()));;
        authorField.setText(String.valueOf(book.getStringAuthor()));
        typeField.setValue(book.getProductType());
        amountField.setText(String.valueOf(book.getIntAmount()));
    }

    public boolean isOkClicked()
    {
        return okClicked;
    }

    public boolean handleCheckBox()    {
        if(isAvailable.isSelected())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            book.setName(titleField.getText());
            book.setAuthor(authorField.getText());
            book.setType((ProductType) typeField.getValue());
            book.setAmount(Integer.parseInt(amountField.getText()));
            book.setAvailable(handleCheckBox());
            okClicked = true;
            dialogStage.close();
            
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().length() == 0)
        {
            errorMessage += "No valid title!\n"; 
        }
        
        if (authorField.getText() == null || authorField.getText().length() == 0)
        {
            errorMessage += "No valid author!\n"; 
        }
 
        if (amountField.getText() == null || amountField.getText().length() == 0)
        {
            errorMessage += "No valid amount!\n"; 
        }

        
        if (amountField.getText() == null || amountField.getText().length() == 0)
        {
            errorMessage += "No valid available!\n"; 
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
           
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