package GUI.adding_medicine;

import GUI.alert_box.AlertBoxForm;
import data.dto.MedicineDTO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AzilUtilities;

public class AddingMedicineController {
    @FXML private TextField nameTextField;
    @FXML private TextField amountTextField;
    @FXML private TextField descriptionTextField;
    private Stage stage;

    public void initialize(Stage stage) {
        this.stage = stage;
    }

    public void addDrug() {
        if(checkName() && checkAmount() && checkDescription()) {
            AzilUtilities.getDAOFactory().getMedicineDAO().insert(new MedicineDTO(0, nameTextField.getText(), descriptionTextField.getText(), Integer.valueOf(amountTextField.getText())));
            displayAlertBox("Lijek je uspješno dodat!");
            stage.close();
        }
    }

    public void quit() {
        stage.close();
    }

    private boolean checkName() {
        if("".equals(nameTextField.getText())) {
            displayAlertBox("Unos za polje naziv nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkAmount() {
        if("".equals(amountTextField.getText()) || !checkNumber(amountTextField.getText())) {
            displayAlertBox("Unos za polje količina nije odgovarajući!");
            return false;
        }
        return true;
    }

    private boolean checkDescription() {
        if("".equals(descriptionTextField.getText())) {
            displayAlertBox("Unos za polje opis nije odgovarajući!");
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

    private void displayAlertBox(String content) {
        try {
            new AlertBoxForm(content).display();
        } catch(Exception exception) {

        }
    }
}
