package GUI.adding_medicine;

import GUI.alert_box.AlertBoxForm;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddingMedicineController {
    @FXML private TextField nameTextField;
    @FXML private DatePicker dateOfManufacturePicker;
    @FXML private DatePicker expirationDatePicker;
    @FXML private TextField amountTextField;
    @FXML private TextField descriptionTextField;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void addDrug() {
        if(checkName() && checkDateOfMaunfacturer() && checkExpirationDate() && checkAmount() && checkDescription()) {
            try {
                new AlertBoxForm("Lijek je uspješno dodat!").display();
            } catch(Exception exception) {

            }

            nameTextField.clear();
            dateOfManufacturePicker.setValue(null);
            expirationDatePicker.setValue(null);
            amountTextField.clear();
            descriptionTextField.clear();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje naziv nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkDateOfMaunfacturer() {
        if (dateOfManufacturePicker.getValue() == null) {
            try {
                new AlertBoxForm("Datum proizvodnje nije izabran!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkExpirationDate() {
        if(expirationDatePicker.getValue() == null) {
            try {
                new AlertBoxForm("Rok trajanja nije izabran!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkAmount() {
        if("".equals(amountTextField.getText()) || !checkNumber(amountTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje količina nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextField.getText())) {
            try {
                new AlertBoxForm("Unos za polje opis nije odgovarajući!").display();
            } catch(Exception exception) {

            }
            return false;
        }
        return true;
    }

    private boolean checkNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException exception) {
            return false;
        }
    }
}
